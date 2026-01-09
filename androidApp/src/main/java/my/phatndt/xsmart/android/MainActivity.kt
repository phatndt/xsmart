package my.phatndt.xsmart.android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.xsmart.share.ui.theme.XSmartTheme
import my.phatndt.xsmart.android.features.bmicalculator.presentation.view.BmiCalculatorHomeRoute
import my.phatndt.xsmart.android.features.bmicalculator.presentation.view.BmiCalculatorRoute
import my.phatndt.xsmart.android.features.dashboard.DashboardRoute
import my.phatndt.xsmart.android.features.dashboard.model.FeatureType
import my.phatndt.xsmart.android.features.vnsalarycalculator.detail.VnSalaryCalculatorDetailRoute
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.VnSalaryCalculatorRoute
import my.xsmart.feature.salarycalculator.SalaryCalculatorActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            XSmartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    XSmart()
                }
            }
        }
    }
}

@Composable
fun XSmart() {
    XSmartNavHost()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun XSmartNavHost(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = Destinations.Dashboard.route,
        route = "/",
    ) {
        composable(route = Destinations.Dashboard.route) {
            DashboardRoute { featureType ->
                when (featureType) {
                    FeatureType.VN_SALARY_CALCULATOR -> {
                        navController.navigate(Destinations.VnSalaryCalculator.VnSalaryCalculatorMain.route)
                    }

                    FeatureType.BMI_CALCULATOR -> {
                        navController.navigate(Destinations.BmiCalculatorHome.route)
                    }

                    FeatureType.VN_SALARY_CALCULATOR_V2 -> {
                        context.startActivity(Intent(context, SalaryCalculatorActivity::class.java))
//                        navController.navigate(SalaryRoutes.GRAPH)
                    }
                }
            }
        }
        composable(Destinations.VnSalaryCalculator.VnSalaryCalculatorMain.route) {
            VnSalaryCalculatorRoute(
                onNavigateToDetail = {
                    navController.navigate(
                        Destinations.VnSalaryCalculator.VnSalaryCalculatorDetail.route,
                    )
                },
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(route = Destinations.VnSalaryCalculator.VnSalaryCalculatorDetail.route) {
            VnSalaryCalculatorDetailRoute {
                navController.popBackStack()
            }
        }

        composable(route = Destinations.BmiCalculator.route) {
            BmiCalculatorRoute {
                navController.popBackStack(Destinations.BmiCalculatorHome.route, false)
            }
        }

        composable(route = Destinations.BmiCalculatorHome.route) {
            BmiCalculatorHomeRoute {
                navController.navigate(Destinations.BmiCalculator.route)
            }
        }
    }
}

sealed class Destinations(open val route: String) {

    sealed class VnSalaryCalculator(override val route: String) : Destinations(route) {

        data object VnSalaryCalculatorMain : VnSalaryCalculator("vn-salary-calculator")

        data object VnSalaryCalculatorDetail : VnSalaryCalculator("detail")
    }

    data object Dashboard : Destinations("dashboard")

    data object UnitConverter : Destinations("unitConverter")
    data object BmiCalculatorHome : Destinations("bmCalculatorHome")
    data object BmiCalculator : Destinations("bmCalculator")
    data object CurrencyConverter : Destinations("currencyConverter")
    data object Compass : Destinations("compass")
}

@Preview
@Composable
fun DefaultPreview() {
    XSmartTheme {
        XSmart()
    }
}
