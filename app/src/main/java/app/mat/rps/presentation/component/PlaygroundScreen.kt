package app.mat.rps.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset

@Composable
fun PlaygroundScreen(
    modifier: Modifier,
    xOffset: Int,
    yOffset: Int,
    movingObject: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .offset {
                    IntOffset(
                        xOffset,
                        yOffset
                    )
                }
                .align(
                    alignment = Alignment.CenterStart
                )
        ) {
            movingObject()
        }
    }
}