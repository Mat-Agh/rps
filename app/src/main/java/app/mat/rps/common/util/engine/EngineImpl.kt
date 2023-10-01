package app.mat.rps.common.util.engine

import app.mat.rps.common.MovementHandler
import app.mat.rps.presentation.state.model.BallState
import app.mat.rps.presentation.state.type.BallType
import app.mat.rps.presentation.state.type.MovementDirection
import app.mat.rps.presentation.state.type.MovementLimitation
import javax.inject.Inject

class EngineImpl @Inject constructor() : Engine {
    //region Companion object
    companion object {
        private const val DEFAULT_BALL_ID = 0

        private const val DEFAULT_BALL_X_POSITION = 0

        private const val DEFAULT_BALL_Y_POSITION = 0
    }
    //endregion Companion Object

    //region Variables
    private lateinit var movementHandler: MovementHandler

    private var screenWidth: Int = 0

    private var screenHeight: Int = 0

    private var screenDensity: Float = 1f

    private var ballSize: Int = (30 * screenDensity).toInt()

    private val screenEndLimit: Int = (ballSize * 1.5).toInt()

    private val screenBottomLimit: Int = (ballSize * 3.5).toInt()

    private var ballEncounterRadius: Int = ballSize * 2

    private var movementDuration: Long = 16

    private var encounterCheckDuration: Long = movementDuration * (ballSize / 10)

    private var movementSteps: Int = (4 * screenDensity).toInt()
    //endregion Variables

    //region Override Methods
    override fun init(
        movementHandler: MovementHandler,
        width: Int,
        height: Int,
        pixelDensity: Float
    ) {
        this.movementHandler = movementHandler
        screenWidth = width
        screenHeight = height
        screenDensity = pixelDensity
    }

    override fun getMovementSteps(): Int = movementSteps

    override fun setMovementSteps(
        movementSteps: Int
    ) {
        this.movementSteps = movementSteps
    }

    override fun getMovementDuration(): Long = movementDuration
    override fun setMovementDuration(
        movementDuration: Long
    ) {
        this.movementDuration = movementDuration
    }

    override fun getEncounterCheckDuration(): Long = encounterCheckDuration

    override fun setEncounterCheckDuration(
        encounterCheckDuration: Long
    ) {
        this.encounterCheckDuration = encounterCheckDuration
    }

    override fun getBallSize(): Int = ballSize
    override fun setBallSize(
        ballSize: Int
    ) {
        this.ballSize = ballSize
    }

    override fun getBallEncounterRadius(): Int = ballEncounterRadius
    override fun setBallEncounterRadius(
        ballEncounterRadius: Int
    ) {
        this.ballEncounterRadius = ballEncounterRadius
    }

    override suspend fun calculateBallMovement(
        ballState: BallState?
    ) {
        ballState ?: return

        val currentDirection = ballState.movementDirection

        val movementLimitation = getMovementLimitations(
            ballState = ballState
        )

        when (movementLimitation) {
            MovementLimitation.TOP -> {
                when (currentDirection) {
                    MovementDirection.TOP_START, MovementDirection.START -> moveBallToBottomStart(
                        ballState = ballState
                    )

                    MovementDirection.TOP_END, MovementDirection.END -> moveBallToBottomEnd(
                        ballState = ballState
                    )

                    else -> moveBallToBottom(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.BOTTOM -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM_START, MovementDirection.START -> moveBallToTopStart(
                        ballState = ballState
                    )

                    MovementDirection.BOTTOM_END, MovementDirection.END -> moveBallToTopEnd(
                        ballState = ballState
                    )

                    else -> moveBallToTop(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.START -> {
                when (currentDirection) {
                    MovementDirection.TOP_START, MovementDirection.TOP -> moveBallToTopEnd(
                        ballState = ballState
                    )

                    MovementDirection.BOTTOM_START, MovementDirection.BOTTOM -> moveBallToBottomEnd(
                        ballState = ballState
                    )

                    else -> moveBallToEnd(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.END -> {
                when (currentDirection) {
                    MovementDirection.TOP_END, MovementDirection.TOP -> moveBallToTopStart(
                        ballState = ballState
                    )

                    MovementDirection.BOTTOM_END, MovementDirection.BOTTOM -> moveBallToBottomStart(
                        ballState = ballState
                    )

                    else -> moveBallToStart(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.START_TOP -> {
                when (currentDirection) {
                    MovementDirection.TOP, MovementDirection.TOP_END -> moveBallToEnd(
                        ballState = ballState
                    )

                    MovementDirection.START, MovementDirection.BOTTOM_START -> moveBallToBottom(
                        ballState = ballState
                    )

                    else -> moveBallToBottomEnd(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.TOP_END -> {
                when (currentDirection) {
                    MovementDirection.TOP, MovementDirection.TOP_START -> moveBallToStart(
                        ballState = ballState
                    )

                    MovementDirection.END, MovementDirection.BOTTOM_END -> moveBallToBottom(
                        ballState = ballState
                    )

                    else -> moveBallToBottomStart(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.BOTTOM_START -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM, MovementDirection.BOTTOM_END -> moveBallToEnd(
                        ballState = ballState
                    )

                    MovementDirection.START, MovementDirection.TOP_START -> moveBallToTop(
                        ballState = ballState
                    )

                    else -> moveBallToTopEnd(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.END_BOTTOM -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM, MovementDirection.BOTTOM_START -> moveBallToStart(
                        ballState = ballState
                    )

                    MovementDirection.END, MovementDirection.TOP_END -> moveBallToTop(
                        ballState = ballState
                    )

                    else -> moveBallToTopStart(
                        ballState = ballState
                    )
                }
            }

            MovementLimitation.NONE -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToTopStart(
                        ballState = ballState
                    )

                    MovementDirection.BOTTOM_START -> moveBallToBottomStart(
                        ballState = ballState
                    )

                    MovementDirection.TOP_END -> moveBallToTopEnd(
                        ballState = ballState
                    )

                    MovementDirection.BOTTOM_END -> moveBallToBottomEnd(
                        ballState = ballState
                    )

                    MovementDirection.TOP -> moveBallToTop(
                        ballState = ballState
                    )

                    MovementDirection.BOTTOM -> moveBallToBottom(
                        ballState = ballState
                    )

                    MovementDirection.START -> moveBallToStart(
                        ballState = ballState
                    )

                    MovementDirection.END -> moveBallToEnd(
                        ballState = ballState
                    )
                }
            }
        }
    }

    override fun getDefaultBallState(
        ballType: BallType
    ) = BallState(
        id = DEFAULT_BALL_ID,
        xPosition = DEFAULT_BALL_X_POSITION,
        yPosition = DEFAULT_BALL_Y_POSITION,
        type = ballType,
        movementDirection = MovementDirection.BOTTOM
    )

    override fun createBallState(
        id: Int,
        ballType: BallType
    ): BallState = BallState(
        id = id,
        xPosition = (ballSize..(screenWidth - ballSize)).random(),
        yPosition = (ballSize..(screenHeight - ballSize)).random(),
        type = ballType,
        movementDirection = MovementDirection.entries.shuffled().first()
    )

    private fun getMovementLimitations(
        ballState: BallState
    ): MovementLimitation {
        val movementLimitation: MovementLimitation

        val isTopLimited = ballState.yPosition < 0
        val isBottomLimited = ballState.yPosition > (screenHeight - screenBottomLimit)
        val isStartLimited = ballState.xPosition < 0
        val isEndLimited = ballState.xPosition > (screenWidth - screenEndLimit)

        movementLimitation = when {
            isStartLimited && isTopLimited -> MovementLimitation.START_TOP
            isTopLimited && isEndLimited -> MovementLimitation.TOP_END
            isEndLimited && isBottomLimited -> MovementLimitation.END_BOTTOM
            isBottomLimited && isStartLimited -> MovementLimitation.BOTTOM_START
            isTopLimited -> MovementLimitation.TOP
            isBottomLimited -> MovementLimitation.BOTTOM
            isStartLimited -> MovementLimitation.START
            isEndLimited -> MovementLimitation.END
            else -> MovementLimitation.NONE
        }

        return movementLimitation
    }

    private suspend fun moveBallToTop(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition,
            yPosition = ballState.yPosition - movementSteps,
            type = ballState.type,
            movementDirection = MovementDirection.TOP
        )
    )

    private suspend fun moveBallToBottom(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition,
            yPosition = ballState.yPosition + movementSteps,
            type = ballState.type,
            movementDirection = MovementDirection.BOTTOM
        )
    )

    private suspend fun moveBallToEnd(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition + movementSteps,
            yPosition = ballState.yPosition,
            type = ballState.type,
            movementDirection = MovementDirection.END
        )

    )

    private suspend fun moveBallToStart(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition - movementSteps,
            yPosition = ballState.yPosition,
            type = ballState.type,
            movementDirection = MovementDirection.START
        )
    )

    private suspend fun moveBallToTopEnd(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition + movementSteps,
            yPosition = ballState.yPosition - movementSteps,
            type = ballState.type,
            movementDirection = MovementDirection.TOP_END
        )
    )

    private suspend fun moveBallToTopStart(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition - movementSteps,
            yPosition = ballState.yPosition - movementSteps,
            type = ballState.type,
            movementDirection = MovementDirection.TOP_START
        )
    )

    private suspend fun moveBallToBottomEnd(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition + movementSteps,
            yPosition = ballState.yPosition + movementSteps,
            type = ballState.type,
            movementDirection = MovementDirection.BOTTOM_END
        )
    )

    private suspend fun moveBallToBottomStart(
        ballState: BallState
    ) = movementHandler.moveBall(
        ballState = BallState(
            id = ballState.id,
            xPosition = ballState.xPosition - movementSteps,
            yPosition = ballState.yPosition + movementSteps,
            type = ballState.type,
            movementDirection = MovementDirection.BOTTOM_START
        )
    )
}