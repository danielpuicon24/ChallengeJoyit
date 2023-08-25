package com.my.training.challengejoyit.ui.view

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.my.training.challengejoyit.ui.login.navigation.Destination
import com.my.training.challengejoyit.ui.login.navigation.NavigationIntent
import com.my.training.challengejoyit.ui.login.navigation.composable
import com.my.training.challengejoyit.ui.login.ui.*
import com.my.training.challengejoyit.ui.login.ui.theme.LoginTheme
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun MainScreen(
    loginviewmodel: LoginViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = loginviewmodel.navigationChannel,
        navHostController = navController
    )
    LoginTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "login"
            ) {
                composable(destination = Destination.LoginScreen) {
                    LoginScreen(loginviewmodel)
                }
                composable(destination = Destination.SigninScreen) {
                    SigninScreen(loginviewmodel)
                }
                composable(destination = Destination.SignupScreen) {
                    SignUpScreen(loginviewmodel)
                }
                composable(destination = Destination.DetailsScreen) {
                    DatosUsuariosScreen(loginviewmodel)
                }
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}