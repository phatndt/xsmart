package my.xsmart.feature.salarycalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import my.xsmart.feature.salarycalculator.ui.input.InputSalaryRoute
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTheme
import my.xsmart.share.ui.theme.XSmartTheme

class SalaryCalculatorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SalaryCalculatorTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = SalaryRoutes.INPUT,
                        route = SalaryRoutes.GRAPH,
                    ) {
                        composable(route = SalaryRoutes.INPUT) {
                            InputSalaryRoute(onNavigateToDetail = {}) { }
                        }
                    }
                }
            }
        }
    }
}
