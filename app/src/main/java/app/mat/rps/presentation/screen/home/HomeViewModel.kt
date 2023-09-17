package app.mat.rps.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    //region Companion object
    companion object {
        private const val BALL_SIZE: Int = 70
        private const val BALL_LIMIT: Int = ((BALL_SIZE * 1) / 2)
        private const val MOVEMENT_SPEED: Int = 5
        private const val MOVEMENT_VELOCITY: Long = 50
    }
    //endregion Companion object

    //region Data Classes
    enum class BallType {
        ROCK,
        PAPER,
        SCISSOR
    }

    enum class MovementDirection {
        TOP_START,
        BOTTOM_START,
        TOP_END,
        BOTTOM_END,
        TOP,
        BOTTOM,
        START,
        END
    }

    data class BallState(
        val xPosition: Int,
        val yPosition: Int,
        val ballType: BallType,
        val movementDirection: MovementDirection
    )
    //endregion Data Classes

    //region Variables
    private var screenWidth = 0;
    private var screenHeight = 0;

    private val _ballState: MutableStateFlow<BallState> = MutableStateFlow(
        BallState(
            xPosition = 100,
            yPosition = 100,
            ballType = BallType.ROCK,
            movementDirection = MovementDirection.BOTTOM_END
        )
    )

    val ballState: StateFlow<BallState> = _ballState.asStateFlow()

    private var ballJob: Job? = Job()
    //endregion Variables

    //region Private Methods
    fun startBallMovement() {
        stopBallMovement()

        ballJob = viewModelScope.launch(
            Dispatchers.IO
        ) {
            while (true) {
                moveBall()
                delay(MOVEMENT_VELOCITY)
            }
        }
    }

    private fun stopBallMovement() {
        ballJob?.cancel()
        ballJob = null
    }

    private suspend fun moveBall() {
        val currentX = ballState.value.xPosition
        val currentY = ballState.value.yPosition
        val currentDirection = ballState.value.movementDirection
        val isAtTopLimit = currentY <= (0 - BALL_LIMIT)
        val isAtBottomLimit = currentY >= (screenHeight - BALL_LIMIT)
        val isAtStartLimit = currentX <= (0 - BALL_LIMIT)
        val isAtEndLimit = currentX >= (screenWidth - BALL_LIMIT)


        when {
            isAtTopLimit && isAtStartLimit -> {
                moveBallToBottomEnd()
            }

            isAtTopLimit && isAtEndLimit -> {
                moveBallToBottomStart()
            }

            isAtBottomLimit && isAtStartLimit -> {
                moveBallToTopEnd()
            }

            isAtBottomLimit && isAtEndLimit -> {
                moveBallToTopStart()
            }

            isAtTopLimit -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToBottomStart()

                    MovementDirection.TOP_END -> moveBallToBottomEnd()

                    else -> moveBallToBottom()
                }
            }

            isAtBottomLimit -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM_START -> moveBallToTopStart()

                    MovementDirection.BOTTOM_END -> moveBallToTopEnd()

                    else -> moveBallToTop()
                }
            }

            isAtStartLimit -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToTopEnd()

                    MovementDirection.BOTTOM_START -> moveBallToBottomEnd()

                    else -> moveBallToEnd()
                }
            }

            isAtEndLimit -> {
                when (currentDirection) {
                    MovementDirection.TOP_END -> moveBallToTopStart()

                    MovementDirection.BOTTOM_END -> moveBallToBottomStart()

                    else -> moveBallToStart()
                }
            }

            else -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToTopStart()
                    MovementDirection.BOTTOM_START -> moveBallToBottomStart()
                    MovementDirection.TOP_END -> moveBallToTopEnd()
                    MovementDirection.BOTTOM_END -> moveBallToBottomEnd()
                    MovementDirection.TOP -> moveBallToTop()
                    MovementDirection.BOTTOM -> moveBallToBottom()
                    MovementDirection.START -> moveBallToStart()
                    MovementDirection.END -> moveBallToEnd()
                }
            }
        }
    }

    private suspend fun moveBallToTop() {
        val ballState = _ballState.value

        withContext(
            Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition,
                yPosition = ballState.yPosition - MOVEMENT_SPEED,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.TOP
            )
        }
    }

    private suspend fun moveBallToBottom() {
        val ballState = _ballState.value

        withContext(
            context = Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition,
                yPosition = ballState.yPosition + MOVEMENT_SPEED,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.BOTTOM
            )
        }
    }

    private suspend fun moveBallToEnd() {
        val ballState = _ballState.value

        withContext(
            Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition + MOVEMENT_SPEED,
                yPosition = ballState.yPosition,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.END
            )
        }
    }

    private suspend fun moveBallToStart() {
        val ballState = _ballState.value

        withContext(
            context = Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition - MOVEMENT_SPEED,
                yPosition = ballState.yPosition,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.START
            )
        }
    }

    private suspend fun moveBallToTopEnd() {
        val ballState = _ballState.value

        withContext(
            context = Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition + MOVEMENT_SPEED,
                yPosition = ballState.yPosition - MOVEMENT_SPEED,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.TOP_END
            )
        }
    }

    private suspend fun moveBallToTopStart() {
        val ballState = _ballState.value

        withContext(
            context = Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition - MOVEMENT_SPEED,
                yPosition = ballState.yPosition - MOVEMENT_SPEED,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.TOP_START
            )
        }
    }

    private suspend fun moveBallToBottomEnd() {
        val ballState = _ballState.value

        withContext(
            context = Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition + MOVEMENT_SPEED,
                yPosition = ballState.yPosition + MOVEMENT_SPEED,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.BOTTOM_END
            )
        }
    }

    private suspend fun moveBallToBottomStart() {
        val ballState = _ballState.value

        withContext(
            context = Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition - MOVEMENT_SPEED,
                yPosition = ballState.yPosition + MOVEMENT_SPEED,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.BOTTOM_START
            )
        }
    }
    //endregion Private Methods

    //region Public Methods
    fun setScreenMeasures(
        width: Int,
        height: Int
    ) {
        screenWidth = width
        screenHeight = height
    }
    //endregion Public Methods
}