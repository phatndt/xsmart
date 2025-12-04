package my.xsmart.feature.salarycalculator

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import my.xsmart.feature.salarycalculator.ui.input.InputSalaryRoute

object SalaryRoutes {
    const val GRAPH = "salary-calculator"
    const val INPUT = "salary-calculator/input"
    const val DETAIL = "salary-calculator/detail"
}

fun NavGraphBuilder.salaryCalculatorNavigation(navController: NavController) {
    navigation(
        startDestination = SalaryRoutes.INPUT,
        route = SalaryRoutes.GRAPH,
    ) {
        composable(route = SalaryRoutes.INPUT) {
            InputSalaryRoute(onNavigateToDetail = {}) {  }
        }
    }
}
