package app.mat.rps.common.util.controller

import app.mat.rps.presentation.state.model.BallState
import app.mat.rps.presentation.state.type.BallType
import app.mat.rps.presentation.state.type.MovementDirection
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

class ControllerImpl @Inject constructor() : Controller {
    //region Companion Object
    companion object {
        private const val DEFAULT_BALL_ID = 0
    }
    //endregion

    //region Override Methods
    override fun getDefaultBallId() = DEFAULT_BALL_ID

    override fun getEncounters(
        ballStateList: List<BallState>,
        ballSize: Int,
        ballEncounterRadius: Int
    ): List<Triple<BallState, BallState, Int?>> {
        val encounterList: MutableList<Triple<BallState, BallState, Int>> = mutableListOf()

        ballStateList.forEach { state1 ->
            ballStateList.forEach { state2 ->
                val existingItem = encounterList.find {
                    (it.first.id == state1.id && it.second.id == state2.id) ||
                            (it.first.id == state2.id && it.second.id == state1.id)
                }

                if (existingItem == null && state1.id != state2.id) {
                    val distance = calculateDistanceBetween(
                        currentBallState = state1,
                        checkBallState = state2,
                        ballSize = ballSize
                    )

                    if (distance < ballEncounterRadius) {
                        val encounterItem = Triple(
                            first = state1,
                            second = state2,
                            third = if (state1.type == state2.type) {
                                DEFAULT_BALL_ID
                            } else {
                                getLoser(
                                    currentBallState = state1,
                                    checkBallState = state2
                                )
                            }
                        )

                        encounterList.add(
                            encounterItem
                        )
                    }
                }
            }
        }

        return encounterList
    }

    override fun getMirrorDirection(
        currentMovementDirection: MovementDirection,
        checkMovementDirection: MovementDirection
    ): MovementDirection = when (checkMovementDirection) {
        MovementDirection.TOP_START -> when (currentMovementDirection) {
            MovementDirection.TOP_START -> MovementDirection.BOTTOM_END
            else -> MovementDirection.TOP_START
        }

        MovementDirection.BOTTOM_START -> when (currentMovementDirection) {
            MovementDirection.BOTTOM_START -> MovementDirection.TOP_END
            else -> MovementDirection.BOTTOM_START
        }

        MovementDirection.TOP_END -> when (currentMovementDirection) {
            MovementDirection.TOP_END -> MovementDirection.BOTTOM_START
            else -> MovementDirection.TOP_END
        }

        MovementDirection.BOTTOM_END -> when (currentMovementDirection) {
            MovementDirection.BOTTOM_END -> MovementDirection.TOP_START
            else -> MovementDirection.BOTTOM_END
        }

        MovementDirection.TOP -> when (currentMovementDirection) {
            MovementDirection.TOP -> MovementDirection.BOTTOM
            else -> listOf(
                MovementDirection.TOP_START,
                MovementDirection.TOP_END
            ).shuffled()
                .first()
        }

        MovementDirection.BOTTOM -> when (currentMovementDirection) {
            MovementDirection.BOTTOM -> MovementDirection.TOP
            else -> listOf(
                MovementDirection.BOTTOM_START,
                MovementDirection.BOTTOM_END
            ).shuffled()
                .first()
        }

        MovementDirection.START -> when (currentMovementDirection) {
            MovementDirection.START -> MovementDirection.END
            else -> listOf(
                MovementDirection.TOP_START,
                MovementDirection.BOTTOM_START
            ).shuffled()
                .first()
        }

        MovementDirection.END -> when (currentMovementDirection) {
            MovementDirection.END -> MovementDirection.START
            else -> listOf(
                MovementDirection.TOP_END,
                MovementDirection.BOTTOM_END
            ).shuffled()
                .first()
        }
    }
    //endregion Override Methods

    //region Private Methods
    private fun calculateDistanceBetween(
        currentBallState: BallState,
        checkBallState: BallState,
        ballSize: Int
    )

            : Int {
        val currentCentralX = currentBallState.xPosition + (ballSize / 2)
        val checkCentralX = checkBallState.xPosition + (ballSize / 2)
        val currentCentralY = currentBallState.yPosition + (ballSize / 2)
        val checkCentralY = checkBallState.yPosition + (ballSize / 2)
        val xDiff: Double = (checkCentralX - currentCentralX).toDouble()
        val yDiff: Double = (checkCentralY - currentCentralY).toDouble()
        return sqrt(xDiff.pow(2.0) + yDiff.pow(2.0)).toInt()
    }

    private fun getLoser(
        currentBallState: BallState,
        checkBallState: BallState
    )

            : Int = when {
        currentBallState.type == BallType.ROCK && checkBallState.type == BallType.PAPER -> currentBallState.id

        currentBallState.type == BallType.ROCK && checkBallState.type == BallType.SCISSORS -> checkBallState.id

        currentBallState.type == BallType.PAPER && checkBallState.type == BallType.ROCK -> checkBallState.id

        currentBallState.type == BallType.PAPER && checkBallState.type == BallType.SCISSORS -> currentBallState.id

        currentBallState.type == BallType.SCISSORS && checkBallState.type == BallType.PAPER -> checkBallState.id

        currentBallState.type == BallType.SCISSORS && checkBallState.type == BallType.ROCK -> currentBallState.id

        else -> currentBallState.id
    }
    //endregion Private Methods
}