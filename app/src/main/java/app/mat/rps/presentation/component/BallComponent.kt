package app.mat.rps.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.mat.rps.presentation.screen.home.HomeViewModel

@Composable
fun BallComponent(
    ballType: HomeViewModel.BallType
) {
    Canvas(
        modifier = Modifier
            .size(
                50.dp
            ),
        onDraw = {
            drawCircle(
                when (ballType) {
                    HomeViewModel.BallType.ROCK -> Color.Black
                    HomeViewModel.BallType.PAPER -> Color.White
                    HomeViewModel.BallType.SCISSOR -> Color.Blue
                }
            )
        }
    )
}

@Preview
@Composable
fun BallComponentPreview() {
    BallComponent(
        ballType = HomeViewModel.BallType.PAPER
    )
}