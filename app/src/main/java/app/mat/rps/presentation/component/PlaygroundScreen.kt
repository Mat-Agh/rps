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
    val rock1State by homeViewModel.rock1State.collectAsState()

    val rock2State by homeViewModel.rock2State.collectAsState()

    val rock3State by homeViewModel.rock3State.collectAsState()

    val rock4State by homeViewModel.rock4State.collectAsState()

    val rock5State by homeViewModel.rock5State.collectAsState()

    val paper1State by homeViewModel.paper1State.collectAsState()

    val paper2State by homeViewModel.paper2State.collectAsState()

    val paper3State by homeViewModel.paper3State.collectAsState()

    val paper4State by homeViewModel.paper4State.collectAsState()

    val paper5State by homeViewModel.paper5State.collectAsState()

    val scissors1State by homeViewModel.scissors1State.collectAsState()

    val scissors2State by homeViewModel.scissors2State.collectAsState()

    val scissors3State by homeViewModel.scissors3State.collectAsState()

    val scissors4State by homeViewModel.scissors4State.collectAsState()

    val scissors5State by homeViewModel.scissors5State.collectAsState()

    Box(
        modifier = modifier.background(
            color = Color.Cyan
        )
    ) {
        if (rock1State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = rock1State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = rock1State.xPosition,
                        y = rock1State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (rock2State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = rock2State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = rock2State.xPosition,
                        y = rock2State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (rock3State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = rock3State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = rock3State.xPosition,
                        y = rock3State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (rock4State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = rock4State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = rock4State.xPosition,
                        y = rock4State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (rock5State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = rock5State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = rock5State.xPosition,
                        y = rock5State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (paper1State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = paper1State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = paper1State.xPosition,
                        y = paper1State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (paper2State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = paper2State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = paper2State.xPosition,
                        y = paper2State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (paper3State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = paper3State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = paper3State.xPosition,
                        y = paper3State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (paper4State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = paper4State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = paper4State.xPosition,
                        y = paper4State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (paper5State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = paper5State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = paper5State.xPosition,
                        y = paper5State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (scissors1State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = scissors1State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = scissors1State.xPosition,
                        y = scissors1State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (scissors2State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = scissors2State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = scissors2State.xPosition,
                        y = scissors2State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (scissors3State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = scissors3State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = scissors3State.xPosition,
                        y = scissors3State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (scissors4State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = scissors4State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = scissors4State.xPosition,
                        y = scissors4State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }

        if (scissors5State.id != 0) {
            BallComponent(
                modifier = modifier,
                ballType = scissors5State.type,
                intOffsetState = animateIntOffsetAsState(
                    targetValue = IntOffset(
                        x = scissors5State.xPosition,
                        y = scissors5State.yPosition
                    ),
                    animationSpec = tween(
                        durationMillis = HomeViewModel.MOVEMENT_DURATION.toInt(),
                        easing = LinearEasing
                    ), label = ""
                )
            )
        }
    }
}