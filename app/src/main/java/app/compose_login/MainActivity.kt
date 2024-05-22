package app.compose_login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import app.compose_login.navigation.NavigationScreen
import app.compose_login.ui.theme.ComposeLoginTheme
import app.compose_login.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLoginTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                   NavigationScreen(viewmodel = viewModel)
                }
            }
        }
    }
}
