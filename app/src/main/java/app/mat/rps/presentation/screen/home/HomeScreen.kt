package app.mat.rps.presentation.screen.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import app.mat.rps.presentation.component.BallComponent
import app.mat.rps.presentation.component.PlaygroundScreen

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    modifier: Modifier
) {
    val ballState by homeViewModel.ballState.collectAsState()

    val intOffsetState = animateIntOffsetAsState(
        targetValue = IntOffset(
            x = ballState.xPosition,
            y = ballState.yPosition
        ),
        animationSpec = tween(
            durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
            easing = LinearEasing
        ), label = ""
    )

    PlaygroundScreen(
        modifier = modifier
            .fillMaxSize(
                fraction = 1f
            ),
        intOffset = intOffsetState.value
    ) {
        BallComponent(
            ballType = ballState.ballType
        )
    }
}