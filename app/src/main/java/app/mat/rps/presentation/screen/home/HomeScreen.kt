package app.mat.rps.presentation.screen.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import app.mat.rps.presentation.component.BallComponent
import app.mat.rps.presentation.component.MoveObjectComponent

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    modifier: Modifier?
) {
    val ballState = homeViewModel.ballState.collectAsState()

    val xOffset = animateIntAsState(
        targetValue = ballState.value.xPosition,
        animationSpec = tween(
            durationMillis = 4000,
            easing = LinearEasing
        ), label = ""
    )

    MoveObjectComponent(
        modifier = (modifier ?: Modifier)
            .fillMaxSize(
                fraction = 0.8f
            ),
        xOffset = xOffset.value
    ) {
        BallComponent(
            ballType = HomeViewModel.BallType.ROCK
        )
    }
}