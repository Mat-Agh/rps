package app.mat.rps.common.util.engine

import app.mat.rps.common.MovementHandler
import app.mat.rps.presentation.state.model.BallState
import app.mat.rps.presentation.state.type.BallType

interface Engine {
    fun init(
        movementHandler: MovementHandler,
        width: Int,
        height: Int,
        pixelDensity: Float
    )

    fun getMovementSteps(): Int

    fun setMovementSteps(
        movementSteps: Int
    )

    fun getMovementDuration(): Long

    fun setMovementDuration(
        movementDuration: Long
    )

    fun getEncounterCheckDuration(): Long

    fun setEncounterCheckDuration(
        encounterCheckDuration: Long
    )

    fun getBallSize(): Int

    fun setBallSize(
        ballSize: Int
    )

    fun getBallEncounterRadius(): Int

    fun setBallEncounterRadius(
        ballEncounterRadius: Int
    )
    fun getDefaultBallState(
        ballType: BallType
    ): BallState

    fun createBallState(
        id: Int,
        ballType: BallType
    ): BallState

    suspend fun calculateBallMovement(
        ballState: BallState?
    )
}