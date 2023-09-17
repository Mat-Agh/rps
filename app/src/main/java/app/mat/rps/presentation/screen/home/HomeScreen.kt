package app.mat.rps.presentation.screen.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
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
    modifier: Modifier,
) {
    val ballState by homeViewModel.ballState.collectAsState()

    val xOffset = animateIntAsState(
        targetValue = ballState.xPosition,
        animationSpec = tween(
            easing = LinearEasing
        ), label = ""
    )
    val yOffset = animateIntAsState(
        targetValue = ballState.yPosition,
        animationSpec = tween(
            easing = LinearEasing
        ), label = ""
    )

    PlaygroundScreen(
        modifier = modifier
            .fillMaxSize(
                fraction = 1f
            ),
        homeViewModel = homeViewModel,
        xOffset = xOffset.value,
        yOffset = yOffset.value
    ) {
        BallComponent(
            ballType = HomeViewModel.BallType.SCISSOR
        )
    }
}