package app.compose_login.navigation

sealed class Destination(val route: String) {

    data object Login : Destination(route = "login")
    data object Home : Destination(route = "home")

    companion object {
        fun getStartDestination() = Login.route
    }

}