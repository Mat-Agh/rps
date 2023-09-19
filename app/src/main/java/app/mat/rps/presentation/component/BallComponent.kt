package app.mat.rps.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import app.mat.rps.presentation.state.enum.BallType

@Composable
fun BallComponent(
    modifier: Modifier,
    ballType: BallType,
    intOffset: State<IntOffset>
) {
    Box(
        modifier = modifier
            .offset {
                intOffset.value
            }
    ) {
        Canvas(
            modifier = Modifier
                .size(
                    50.dp
                ),
            onDraw = {
                drawCircle(
                    color = when (ballType) {
                        BallType.ROCK -> Color.Black
                        BallType.PAPER -> Color.Blue
                        BallType.SCISSOR -> Color.Red
                    }
                )
            }
        )
    }
}