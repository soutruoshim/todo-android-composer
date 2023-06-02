package com.srhdp.to_docompose.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.srhdp.to_docompose.ui.screen.splash.SplashScreen
import com.srhdp.to_docompose.util.Constants.SPLASH_SCREEN


fun NavGraphBuilder.splashComposable(
    navigateToListScreen:() -> Unit,
){
    composable(
        route = SPLASH_SCREEN,
    ){navBackStackEntry->
        SplashScreen(navigateToListScreen)
    }
}