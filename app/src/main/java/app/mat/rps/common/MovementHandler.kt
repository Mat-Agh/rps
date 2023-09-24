package app.mat.rps.common

import app.mat.rps.presentation.state.model.BallState

interface MovementHandler {
    suspend fun moveBall(
        ballState: BallState
    )
}