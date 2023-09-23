package app.mat.rps.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import app.mat.rps.presentation.component.PlaygroundScreen

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    modifier: Modifier
) {
    val context = LocalContext.current

    context.resources.displayMetrics.apply {
        HomeViewModel.setScreenMeasures(
            width = widthPixels,
            height = heightPixels,
            pixelDensity = density
        )
    }

    homeViewModel.createPlayGround()

    PlaygroundScreen(
        modifier = modifier
            .fillMaxSize(
                fraction = 1f
            ),
        homeViewModel = homeViewModel
    )

    homeViewModel.start()
}