package app.mat.rps.common.util.controller

import app.mat.rps.presentation.state.model.BallState
import app.mat.rps.presentation.state.type.MovementDirection

interface Controller {
    fun getDefaultBallId(): Int
    fun getEncounters(
        ballStateList: List<BallState>,
        ballSize: Int,
        ballEncounterRadius: Int
    ): List<Triple<BallState, BallState, Int?>>

    fun getMirrorDirection(
        currentMovementDirection: MovementDirection,
        checkMovementDirection: MovementDirection
    ): MovementDirection
}