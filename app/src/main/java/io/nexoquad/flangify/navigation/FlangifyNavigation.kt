package io.nexoquad.flangify.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import io.nexoquad.flangify.ui.screens.RootScreen
import io.nexoquad.flangify.ui.screens.home.HomeScreen
import io.nexoquad.flangify.ui.screens.home.settings.SettingsScreen
import io.nexoquad.flangify.ui.screens.home.settings.about.AboutAppScreen
import io.nexoquad.flangify.ui.screens.home.settings.feedback.FeedbackScreen
import io.nexoquad.flangify.ui.screens.home.settings.regulatory.RegulatoryScreen
import io.nexoquad.flangify.ui.screens.localization.LocalizationScreen
import io.nexoquad.flangify.ui.screens.other.OtherScreen
import io.nexoquad.flangify.ui.screens.other.appmanager.AppManagerScreen
import io.nexoquad.flangify.ui.screens.other.updatecenter.UpdateCenterScreen
import io.nexoquad.flangify.ui.screens.services.ServicesScreen

internal sealed class FlangifyDestinations(val route: String) {
    fun createRoute(root: FlangifyDestinations) = "${root.route}/$route"
    fun createNestedRoute(nestedRoute: String) = "$nestedRoute/$route"

    object Root : FlangifyDestinations("root")

    object Home : FlangifyDestinations("home")
    object Localization : FlangifyDestinations("localization")
    object Services : FlangifyDestinations("services")
    object Other : FlangifyDestinations("other")

    object Settings : FlangifyDestinations("settings")
    object Regulatory : FlangifyDestinations("regulatory")
    object Feedback : FlangifyDestinations("feedback")
    object AboutApp : FlangifyDestinations("aboutapp")

    object UpdateCenter : FlangifyDestinations("updatecenter")
    object AppManager : FlangifyDestinations("appmanager")
}

@Composable
fun FlangifyNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val homeRoute = FlangifyDestinations.Home.createRoute(FlangifyDestinations.Root)
    val settingsRoute = FlangifyDestinations.Settings.createNestedRoute(homeRoute)

    val otherRoute = FlangifyDestinations.Other.createRoute(FlangifyDestinations.Root)
    val parentStack by navController.currentBackStackEntryAsState()
    val parentDestRoute = parentStack?.destination?.route
    Log.e("Navigation", "parent route: $parentDestRoute")

    NavHost(
        navController = navController,
        startDestination = FlangifyDestinations.Root.route,
        modifier = modifier
    ) {
        addRoot(navController)
        addSettings(navController, settingsRoute)
        addRegulatory(navController, settingsRoute)
        addAboutApp(navController, settingsRoute)
        addFeedback(navController, settingsRoute)
        addAppManager(navController, otherRoute)
        addUpdateCenter(navController, otherRoute)
    }
}

@Composable
fun NestedNavigation(
    childNavController: NavHostController,
    parentNavController: NavHostController,
    modifier: Modifier = Modifier
) {

    val childStack by childNavController.currentBackStackEntryAsState()

    val childDestRoute = childStack?.destination?.route

    Log.e("Navigation", "child route: $childDestRoute")

    NavHost(
        navController = childNavController,
        startDestination = FlangifyDestinations.Home.createRoute(FlangifyDestinations.Root),
        modifier = modifier
    ) {
        addHome(
            parentNavController, FlangifyDestinations.Root
        )
        addLocalization(
            parentNavController, FlangifyDestinations.Root
        )
        addServices(
            parentNavController, FlangifyDestinations.Root
        )
        addOther(
            parentNavController, FlangifyDestinations.Root
        )
    }
}

private fun NavGraphBuilder.addRoot(navController: NavHostController) {
    composable(
        route = FlangifyDestinations.Root.route,
    ) {
        RootScreen(navController)
    }
}


private fun NavGraphBuilder.addHome(navController: NavController, root: FlangifyDestinations) {
    val nestedRoute = FlangifyDestinations.Home.createRoute(root)
    composable(nestedRoute) {
        HomeScreen(openSettings = {
            navController.navigate(
                route = FlangifyDestinations.Settings.createNestedRoute(
                    nestedRoute
                )
            )
        })
    }
}

private fun NavGraphBuilder.addLocalization(
    navController: NavController, root: FlangifyDestinations
) {
    composable(FlangifyDestinations.Localization.createRoute(root)) {
        LocalizationScreen()
    }
}

private fun NavGraphBuilder.addServices(navController: NavController, root: FlangifyDestinations) {
    composable(FlangifyDestinations.Services.createRoute(root)) {
        ServicesScreen()
    }
}

private fun NavGraphBuilder.addOther(navController: NavController, root: FlangifyDestinations) {
    val otherRoute = FlangifyDestinations.Other.createRoute(root)
    composable(FlangifyDestinations.Other.createRoute(root)) {
        OtherScreen(openAppManager = {
            navController.navigate(FlangifyDestinations.AppManager.createNestedRoute(otherRoute))
        }, openUpdateCenter = {
            navController.navigate(FlangifyDestinations.UpdateCenter.createNestedRoute(otherRoute))
        })
    }
}

private fun NavGraphBuilder.addSettings(navController: NavController, route: String) {
    composable(route) {
        SettingsScreen(onBackPressed = navController::navigateUp, openAboutApp = {
            navController.navigate(FlangifyDestinations.AboutApp.createNestedRoute(route))
        }, openFeedback = {
            navController.navigate(FlangifyDestinations.Feedback.createNestedRoute(route))
        }, openRegulatory = {
            navController.navigate(FlangifyDestinations.Regulatory.createNestedRoute(route))
        })
    }
}

private fun NavGraphBuilder.addRegulatory(navController: NavController, route: String) {
    composable(FlangifyDestinations.Regulatory.createNestedRoute(route)) {
        RegulatoryScreen(
            onBackPressed = navController::navigateUp
        )
    }
}

private fun NavGraphBuilder.addAboutApp(navController: NavController, route: String) {
    composable(FlangifyDestinations.AboutApp.createNestedRoute(route)) {
        AboutAppScreen(
            onBackPressed = navController::navigateUp
        )
    }
}

private fun NavGraphBuilder.addFeedback(navController: NavController, route: String) {
    composable(FlangifyDestinations.Feedback.createNestedRoute(route)) {
        FeedbackScreen(
            onBackPressed = navController::navigateUp
        )
    }
}

private fun NavGraphBuilder.addUpdateCenter(navController: NavController, route: String) {
    composable(FlangifyDestinations.UpdateCenter.createNestedRoute(route)) {
        UpdateCenterScreen(
            onBackPressed = navController::navigateUp
        )
    }
}

private fun NavGraphBuilder.addAppManager(navController: NavController, route: String) {
    composable(FlangifyDestinations.AppManager.createNestedRoute(route)) {
        AppManagerScreen(
            onBackPressed = navController::navigateUp
        )
    }
}