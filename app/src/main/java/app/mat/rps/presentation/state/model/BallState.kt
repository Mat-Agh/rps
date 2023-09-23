package app.mat.rps.presentation.state.model

import app.mat.rps.presentation.state.type.BallType
import app.mat.rps.presentation.state.type.MovementDirection

data class BallState(
    val id: Int,
    val xPosition: Int,
    val yPosition: Int,
    val type: BallType,
    val movementDirection: MovementDirection
)