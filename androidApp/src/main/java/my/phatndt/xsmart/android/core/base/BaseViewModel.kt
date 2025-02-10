package my.phatndt.xsmart.android.core.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<State : UiState, Intent : UiIntent, SideEffects : UiSideEffects> :
    ViewModel() {

    @Suppress("PropertyName")
    protected val TAG = this::class.simpleName

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    private val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Intent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<SideEffects> = Channel()
    val effect = _effect.receiveAsFlow()

    protected open val intentExceptionHandler =
        CoroutineExceptionHandler(::handleIntentCoroutineExceptionHandler)

    init {
        subscribeIntentHandler()
    }

    protected fun setUiState(reducer: State.() -> State) {
        val newState = uiState.value.reducer()
        _uiState.update {
            newState
        }
    }

    fun setIntent(event: Intent) {
        val newEvent = event
        viewModelScope.launch {
            _event.emit(newEvent)
        }
    }

    protected fun handleSideEffects(builder: () -> SideEffects) {
        val effectValue = builder()
        viewModelScope.launch {
            _effect.send(effectValue)
        }
    }

    private fun subscribeIntentHandler() {
        viewModelScope.launch(context = intentExceptionHandler) {
            event.collect {
                handleIntent(it)
            }
        }
    }

    protected abstract fun handleIntent(intent: Intent)

    open fun handleIntentCoroutineExceptionHandler(context: CoroutineContext, exception: Throwable) {
        Log.d(TAG, exception.toString())
    }

    protected fun CoroutineScope.launchWithoutException(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit,
    ): Job = launch(
        context = context + intentExceptionHandler,
        start = start,
    ) {
        block.invoke(this)
    }
}
