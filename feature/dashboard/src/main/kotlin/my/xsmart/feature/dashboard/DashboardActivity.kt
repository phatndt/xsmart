package my.xsmart.feature.dashboard

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import my.xsmart.feature.dashboard.ui.main.DashboardNavigationEvent
import my.xsmart.feature.dashboard.ui.main.DashboardRoute
import my.xsmart.share.ui.theme.AppTheme

class DashboardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = DashboardRoutes.MAIN,
                        route = DashboardRoutes.GRAPH,
                    ) {
                        composable(DashboardRoutes.MAIN) {
                            DashboardRoute { navigationEvent ->
                                when (navigationEvent) {
                                    DashboardNavigationEvent.NavigateToEditTools -> TODO()
                                    DashboardNavigationEvent.NavigateToHistory -> TODO()
                                    DashboardNavigationEvent.NavigateToHolidays -> TODO()
                                    DashboardNavigationEvent.NavigateToInsurance -> TODO()
                                    DashboardNavigationEvent.NavigateToNotifications -> TODO()
                                    DashboardNavigationEvent.NavigateToOvertimeTracker -> TODO()
                                    DashboardNavigationEvent.NavigateToSalaryCalculator -> {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("app://bmi"))
                                        startActivity(intent)
                                    }
                                    DashboardNavigationEvent.NavigateToTaxInfo -> TODO()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
