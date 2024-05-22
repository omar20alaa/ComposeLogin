package app.compose_login.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.compose_login.ui.login.Home
import app.compose_login.ui.login.Login
import app.compose_login.viewmodel.LoginViewModel


@Composable
fun NavigationScreen(viewmodel: LoginViewModel) {

    val navController = rememberNavController()
    val loadingProgressBar = viewmodel.progressBar.value
    val imageError = viewmodel.imageErrorAuth.value

    NavHost(
        navController = navController,
        startDestination = Destination.getStartDestination()
    ) {
        composable(route = Destination.Login.route) {
            if (viewmodel.isSuccessLoading.value) {
                LaunchedEffect(key1 = Unit) {
                    navController.navigate(route = Destination.Home.route) {
                        popUpTo(route = Destination.Login.route) {
                            inclusive = true
                        }
                    }
                }
            } else {
                Login(
                    loadingProgressBar = loadingProgressBar,
                    onClickLogin = viewmodel::login,
                    imageError = imageError
                )
            }
        }
        composable(route = Destination.Home.route) {
            Home()
        }
    }
}