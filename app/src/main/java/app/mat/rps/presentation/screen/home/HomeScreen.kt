package app.mat.rps.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import app.mat.rps.presentation.component.BallComponent
import app.mat.rps.presentation.component.PlaygroundScreen

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    modifier: Modifier
) {
    val ballState by homeViewModel.ballState.collectAsState()

    PlaygroundScreen(
        modifier = modifier
            .fillMaxSize(
                fraction = 1f
            ),
        xOffset = ballState.xPosition,
        yOffset = ballState.yPosition
    ) {
        BallComponent(
            ballType = HomeViewModel.BallType.SCISSOR
        )
    }
}