package app.mat.rps.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import app.mat.rps.presentation.screen.home.HomeViewModel

@Composable
fun PlaygroundScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel
) {
    val ballState by homeViewModel.ballState.collectAsState()

    Box(
        modifier = modifier
            .background(
                color = Color.Cyan
            )
    ) {
        BallComponent(
            modifier = modifier,
            ballType = ballState[0].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[0].xPosition,
                    y = ballState[0].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[1].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[1].xPosition,
                    y = ballState[1].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[2].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[2].xPosition,
                    y = ballState[2].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[3].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[3].xPosition,
                    y = ballState[3].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[4].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[4].xPosition,
                    y = ballState[4].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[5].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[5].xPosition,
                    y = ballState[5].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[6].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[6].xPosition,
                    y = ballState[6].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[7].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[7].xPosition,
                    y = ballState[7].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[8].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[8].xPosition,
                    y = ballState[8].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[9].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[9].xPosition,
                    y = ballState[9].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[10].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[10].xPosition,
                    y = ballState[10].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[11].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[11].xPosition,
                    y = ballState[11].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[12].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[12].xPosition,
                    y = ballState[12].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[13].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[13].xPosition,
                    y = ballState[13].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )

        BallComponent(
            modifier = modifier,
            ballType = ballState[14].ballType,
            intOffset = animateIntOffsetAsState(
                targetValue = IntOffset(
                    x = ballState[14].xPosition,
                    y = ballState[14].yPosition
                ),
                animationSpec = tween(
                    durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                    easing = LinearEasing
                ), label = ""
            )
        )
    }
}