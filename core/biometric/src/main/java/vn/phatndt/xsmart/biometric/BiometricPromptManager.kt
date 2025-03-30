package vn.phatndt.xsmart.biometric

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class BiometricPromptManager(private val fragmentActivity: FragmentActivity) {

    private val _biometricResultChannel = Channel<BiometricResult>(Channel.BUFFERED)
    val biometricResultFlow = _biometricResultChannel.receiveAsFlow()

    private val biometricManager: BiometricManager by lazy {
        BiometricManager.from(fragmentActivity)
    }

    fun startBiometricPrompt() {
        when (val authStatus = biometricManager.canAuthenticate(BIOMETRIC_AUTHENTICATORS)) {
            BiometricManager.BIOMETRIC_SUCCESS -> authenticate()
            else -> _biometricResultChannel.trySend(authStatus.toBiometricResult())
        }
    }

    private fun authenticate() {
        val promptInfo = PromptInfo
            .Builder()
            .setTitle("Authentication")
            .setDescription("Please authenticate")
            .setNegativeButtonText("Cancel")
            .setAllowedAuthenticators(BIOMETRIC_AUTHENTICATORS)
            .build()

        val prompt = BiometricPrompt(
            fragmentActivity, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    _biometricResultChannel.trySend(
                        BiometricResult.AuthenticationError(
                            errorCode, errString.toString()
                        )
                    )
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    _biometricResultChannel.trySend(BiometricResult.AuthenticationSucceeded)
                }

                override fun onAuthenticationFailed() {
                    _biometricResultChannel.trySend(BiometricResult.AuthenticationFailed)
                }
            })
        prompt.authenticate(promptInfo)
    }

    private fun Int.toBiometricResult(): BiometricResult = when (this) {
        BiometricManager.BIOMETRIC_SUCCESS -> BiometricResult.Success
        BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> BiometricResult.StatusUnknown
        BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> BiometricResult.ErrorUnsupported
        BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> BiometricResult.ErrorHardwareUnavailable
        BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> BiometricResult.ErrorNoneEnrolled
        BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> BiometricResult.ErrorNoHardware
        BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> BiometricResult.ErrorSecurityUpdateRequired
        else -> BiometricResult.UnknownError
    }

    sealed interface BiometricResult {
        data object Success : BiometricResult
        data object StatusUnknown : BiometricResult
        data object ErrorUnsupported : BiometricResult
        data object ErrorHardwareUnavailable : BiometricResult
        data object ErrorNoneEnrolled : BiometricResult
        data object ErrorNoHardware : BiometricResult
        data object ErrorSecurityUpdateRequired : BiometricResult
        data object UnknownError : BiometricResult
        data class AuthenticationError(val code: Int, val message: String) : BiometricResult
        data object AuthenticationSucceeded : BiometricResult
        data object AuthenticationFailed : BiometricResult
    }

    companion object {
        private const val BIOMETRIC_AUTHENTICATORS = BiometricManager.Authenticators.BIOMETRIC_STRONG
    }
}
