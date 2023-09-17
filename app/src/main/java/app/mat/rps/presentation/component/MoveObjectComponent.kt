package app.mat.rps.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MoveObjectComponent(
    modifier: Modifier?,
    xOffset: Int,
    movingObject: @Composable () -> Unit
) {
    Box(
        modifier = modifier ?: Modifier
    ) {
        Box(
            modifier = (modifier ?: Modifier)
                .offset(
                    x = xOffset.dp
                )
                .align(
                    alignment = Alignment.CenterStart
                )
        ) {
            movingObject()
        }
    }
}

@Preview
@Composable
fun MoveObjectComponentPreview() {
    MoveObjectComponent(
        modifier = Modifier,
        xOffset = 0
    ) {
        BallComponentPreview()
    }
}