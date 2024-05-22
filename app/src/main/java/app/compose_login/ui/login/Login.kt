package app.compose_login.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import app.compose_login.ui.login.components.ButtonLogin
import app.compose_login.ui.login.components.EmailOutTextField
import app.compose_login.ui.login.components.ErrorImageAuth
import app.compose_login.ui.login.components.ImageLogin
import app.compose_login.ui.login.components.PasswordOutTextField
import app.compose_login.ui.login.components.ProgressBarLoading
import app.compose_login.ui.login.components.TextLogin

@Composable
fun Login(
    modifier: Modifier = Modifier,
    loadingProgressBar: Boolean,
    onClickLogin: (email: String, password: String) -> Unit,
    imageError: Boolean
) {

    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val isValidate = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    LaunchedEffect(email, password) {
        isValidate.value = email.isNotBlank() && password.isNotBlank()
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageLogin()
        TextLogin()
        Spacer(modifier = Modifier.height(15.dp))

        EmailOutTextField(
            textValue = email,
            onValueChange = { email = it },
            onClickButton = { email = "" },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = Modifier.height(15.dp))

        PasswordOutTextField(
            textValue = password,
            onValueChange = { password = it },
            onDone = {
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = Modifier.height(35.dp))

        ButtonLogin(
            onClick = { onClickLogin(email, password) },
            enabled = isValidate.value
        )

        Spacer(modifier = Modifier.height(20.dp))
    }

    ErrorImageAuth(isImageValidate = imageError)
    ProgressBarLoading(isLoading = loadingProgressBar)

}