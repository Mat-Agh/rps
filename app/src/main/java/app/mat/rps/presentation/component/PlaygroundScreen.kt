package app.mat.rps.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import app.mat.rps.presentation.screen.home.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun PlaygroundScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    xOffset: Int,
    yOffset: Int,
    movingObject: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .offset(
                    x = xOffset.dp,
                    y = yOffset.dp
                )
                .align(
                    alignment = Alignment.CenterStart
                )
        ) {
            movingObject()

            val configuration = LocalConfiguration.current

            homeViewModel.setScreenMeasures(
                width = configuration.screenWidthDp,
                height = configuration.screenHeightDp
            )
        }
    }
}