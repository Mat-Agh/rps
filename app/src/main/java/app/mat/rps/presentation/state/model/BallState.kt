package app.mat.rps.presentation.state.model

import app.mat.rps.presentation.state.enum.BallType
import app.mat.rps.presentation.state.enum.MovementDirection

data class BallState(
    val xPosition: Int,
    val yPosition: Int,
    val type: BallType,
    val movementDirection: MovementDirection
)