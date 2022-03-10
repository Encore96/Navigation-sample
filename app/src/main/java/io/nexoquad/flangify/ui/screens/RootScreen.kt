package io.nexoquad.flangify.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.nexoquad.flangify.R
import io.nexoquad.flangify.navigation.FlangifyDestinations
import io.nexoquad.flangify.navigation.NestedNavigation

@Composable
fun RootScreen(parentNavController: NavHostController) {
    val childNavController = rememberNavController()
    val currentSelectedItem by childNavController.currentScreenAsState()

    Scaffold(bottomBar = {
        RootBottomNavigation(
            selectedNavigation = currentSelectedItem, onNavigationSelected = { selected ->
                childNavController.navigate(selected.createRoute(FlangifyDestinations.Root)) {
                    launchSingleTop = true
                    restoreState = true

                    popUpTo(childNavController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            }, modifier = Modifier.fillMaxWidth()
        )

    }, modifier = Modifier.fillMaxSize()) { paddingValues ->
        NestedNavigation(
            childNavController = childNavController,
            parentNavController = parentNavController,
            modifier = Modifier.fillMaxHeight()
        )
    }
}

@Stable
@Composable
private fun NavController.currentScreenAsState(): State<FlangifyDestinations> {
    val selectedItem = remember { mutableStateOf<FlangifyDestinations>(FlangifyDestinations.Home) }

    DisposableEffect(this) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            when {
                destination.hierarchy.any {
                    it.route == FlangifyDestinations.Home.createRoute(FlangifyDestinations.Root)
                } -> {
                    selectedItem.value = FlangifyDestinations.Home
                }
                destination.hierarchy.any {
                    it.route == FlangifyDestinations.Localization.createRoute(FlangifyDestinations.Root)
                } -> {
                    selectedItem.value = FlangifyDestinations.Localization
                }
                destination.hierarchy.any {
                    it.route == FlangifyDestinations.Services.createRoute(FlangifyDestinations.Root)
                } -> {
                    selectedItem.value = FlangifyDestinations.Services
                }
                destination.hierarchy.any {
                    it.route == FlangifyDestinations.Other.createRoute(FlangifyDestinations.Root)
                } -> {
                    selectedItem.value = FlangifyDestinations.Other
                }
            }
        }
        addOnDestinationChangedListener(listener)

        onDispose {
            removeOnDestinationChangedListener(listener)
        }
    }

    return selectedItem
}

@Composable
internal fun RootBottomNavigation(
    selectedNavigation: FlangifyDestinations,
    onNavigationSelected: (FlangifyDestinations) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(color = MaterialTheme.colors.background, modifier = modifier) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = contentColorFor(MaterialTheme.colors.surface),
            elevation = 0.dp,
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
        ) {
            NestedNavigationItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        NestedNavigationItemIcon(
                            item = item, selected = selectedNavigation == item.screen
                        )
                    },
                    label = { Text(text = stringResource(item.labelResId)) },
                    selected = selectedNavigation == item.screen,
                    onClick = { onNavigationSelected(item.screen) },
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = MaterialTheme.colors.onSurface
                )
            }
        }
    }
}

@Composable
private fun NestedNavigationItemIcon(item: NestedNavigationItem, selected: Boolean) {
    val painter = when (item) {
        is NestedNavigationItem.ResourceIcon -> painterResource(item.iconResId)
        is NestedNavigationItem.ImageVectorIcon -> rememberVectorPainter(item.iconImageVector)
    }
    val selectedPainter = when (item) {
        is NestedNavigationItem.ResourceIcon -> painterResource(item.iconResId)
        is NestedNavigationItem.ImageVectorIcon -> rememberVectorPainter(
            item.iconImageVector
        )
    }

    Icon(
        painter = if (selected) selectedPainter else painter,
        contentDescription = stringResource(item.contentDescriptionResId),
    )
}

private val NestedNavigationItems = listOf(
    NestedNavigationItem.ImageVectorIcon(
        screen = FlangifyDestinations.Home,
        labelResId = R.string.title_home,
        contentDescriptionResId = R.string.title_home,
        iconImageVector = Icons.Default.Home
    ), NestedNavigationItem.ImageVectorIcon(
        screen = FlangifyDestinations.Localization,
        labelResId = R.string.title_localization,
        contentDescriptionResId = R.string.title_localization,
        iconImageVector = Icons.Default.Place
    ), NestedNavigationItem.ImageVectorIcon(
        screen = FlangifyDestinations.Services,
        labelResId = R.string.title_services,
        contentDescriptionResId = R.string.title_services,
        iconImageVector = Icons.Default.AccountBox
    ), NestedNavigationItem.ImageVectorIcon(
        screen = FlangifyDestinations.Other,
        labelResId = R.string.title_other,
        contentDescriptionResId = R.string.title_other,
        iconImageVector = Icons.Default.Face
    )
)

private sealed class NestedNavigationItem(
    val screen: FlangifyDestinations,
    @StringRes val labelResId: Int,
    @StringRes val contentDescriptionResId: Int,
) {
    class ResourceIcon(
        screen: FlangifyDestinations,
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        @DrawableRes val iconResId: Int,
    ) : NestedNavigationItem(screen, labelResId, contentDescriptionResId)

    class ImageVectorIcon(
        screen: FlangifyDestinations,
        @StringRes labelResId: Int,
        @StringRes contentDescriptionResId: Int,
        val iconImageVector: ImageVector,
    ) : NestedNavigationItem(screen, labelResId, contentDescriptionResId)
}
