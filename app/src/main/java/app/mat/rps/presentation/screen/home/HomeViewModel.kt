package app.mat.rps.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mat.rps.presentation.state.enum.BallType
import app.mat.rps.presentation.state.enum.MovementDirection
import app.mat.rps.presentation.state.model.BallState
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
        private var screenWidth: Int = 0
        private var screenHeight: Int = 0
        private var screenDensity: Float = 1f
        private val ballSize: Int = (50 * screenDensity).toInt()
        private val endLimit = (ballSize * 2)
        private val bottomLimit = (ballSize * 2) + ((ballSize * 2) / 3)
        private val movementSteps: Int = (16 * screenDensity).toInt()
        const val MOVEMENT_DURATION: Long = 8

        fun setScreenMeasures(
            width: Int,
            height: Int,
            pixelDensity: Float
        ) {
            screenWidth = width
            screenHeight = height
            screenDensity = pixelDensity
        }
    }
    //endregion Companion object

    //region Variables
    private val _ballState: MutableStateFlow<List<BallState>> = MutableStateFlow(
        listOf()
    )


    val ballState: StateFlow<List<BallState>> = _ballState.asStateFlow()

    private var ballJob: Job? = Job()
    //endregion Variables

    //region Private Methods
    fun startBallMovement() {
        stopBallMovement()

        ballJob = viewModelScope.launch(
            Dispatchers.IO
        ) {
            while (true) {
                ballState.value.forEach { ballState ->
                    moveBall(ballState)
                }
                delay(MOVEMENT_DURATION)
            }
        }
    }

    private fun stopBallMovement() {
        ballJob?.cancel()
        ballJob = null
    }

    private suspend fun moveBall(
        ball: BallState
    ) {
        val currentX = ball.xPosition
        val currentY = ball.yPosition
        val currentDirection = ball.movementDirection
        val isAtTopLimit = currentY <= 0
        val isAtBottomLimit = currentY >= (screenHeight - bottomLimit)
        val isAtStartLimit = currentX <= 0
        val isAtEndLimit = currentX >= (screenWidth - endLimit)


        when {
            isAtTopLimit && isAtStartLimit -> {
                moveBallToBottomEnd(
                    ball = ball
                )
            }

            isAtTopLimit && isAtEndLimit -> {
                moveBallToBottomStart(
                    ball = ball
                )
            }

            isAtBottomLimit && isAtStartLimit -> {
                moveBallToTopEnd(
                    ball = ball
                )
            }

            isAtBottomLimit && isAtEndLimit -> {
                moveBallToTopStart(
                    ball = ball
                )
            }

            isAtTopLimit -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToBottomStart(
                        ball = ball
                    )

                    MovementDirection.TOP_END -> moveBallToBottomEnd(
                        ball = ball
                    )

                    else -> moveBallToBottom(
                        ball = ball
                    )
                }
            }

            isAtBottomLimit -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM_START -> moveBallToTopStart(
                        ball = ball
                    )

                    MovementDirection.BOTTOM_END -> moveBallToTopEnd(
                        ball = ball
                    )

                    else -> moveBallToTop(
                        ball = ball
                    )
                }
            }

            isAtStartLimit -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToTopEnd(
                        ball = ball
                    )

                    MovementDirection.BOTTOM_START -> moveBallToBottomEnd(
                        ball = ball
                    )

                    else -> moveBallToEnd(
                        ball = ball
                    )
                }
            }

            isAtEndLimit -> {
                when (currentDirection) {
                    MovementDirection.TOP_END -> moveBallToTopStart(
                        ball = ball
                    )

                    MovementDirection.BOTTOM_END -> moveBallToBottomStart(
                        ball = ball
                    )

                    else -> moveBallToStart(
                        ball = ball
                    )
                }
            }

            else -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToTopStart(
                        ball = ball
                    )

                    MovementDirection.BOTTOM_START -> moveBallToBottomStart(
                        ball = ball
                    )

                    MovementDirection.TOP_END -> moveBallToTopEnd(
                        ball = ball
                    )

                    MovementDirection.BOTTOM_END -> moveBallToBottomEnd(
                        ball = ball
                    )

                    MovementDirection.TOP -> moveBallToTop(
                        ball = ball
                    )

                    MovementDirection.BOTTOM -> moveBallToBottom(
                        ball = ball
                    )

                    MovementDirection.START -> moveBallToStart(
                        ball = ball
                    )

                    MovementDirection.END -> moveBallToEnd(
                        ball = ball
                    )
                }
            }
        }
    }

    private suspend fun moveBallToTop(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition,
                yPosition = ball.yPosition - movementSteps,
                ballType = ball.ballType,
                movementDirection = MovementDirection.TOP
            )

            _ballState.value = ballList
        }
    }

    private suspend fun moveBallToBottom(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            context = Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition,
                yPosition = ball.yPosition + movementSteps,
                ballType = ball.ballType,
                movementDirection = MovementDirection.BOTTOM
            )

            _ballState.value = ballList
        }
    }

    private suspend fun moveBallToEnd(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition,
                ballType = ball.ballType,
                movementDirection = MovementDirection.END
            )

            _ballState.value = ballList
        }
    }

    private suspend fun moveBallToStart(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            context = Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition,
                ballType = ball.ballType,
                movementDirection = MovementDirection.START
            )

            _ballState.value = ballList
        }
    }

    private suspend fun moveBallToTopEnd(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            context = Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition - movementSteps,
                ballType = ball.ballType,
                movementDirection = MovementDirection.TOP_END
            )

            _ballState.value = ballList
        }
    }

    private suspend fun moveBallToTopStart(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            context = Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition - movementSteps,
                ballType = ball.ballType,
                movementDirection = MovementDirection.TOP_START
            )

            _ballState.value = ballList
        }
    }

    private suspend fun moveBallToBottomEnd(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            context = Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition + movementSteps,
                ballType = ball.ballType,
                movementDirection = MovementDirection.BOTTOM_END
            )

            _ballState.value = ballList
        }
    }

    private suspend fun moveBallToBottomStart(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            context = Dispatchers.Main
        ) {
            ballList[ball.id] = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition + movementSteps,
                ballType = ball.ballType,
                movementDirection = MovementDirection.BOTTOM_START
            )

            _ballState.value = ballList
        }
    }
    //endregion Private Methods

    //region Public Methods
    fun createBalls() {
        val balls: MutableList<BallState> = mutableListOf()

        (0..4).forEach { i ->
            balls.add(
                BallState(
                    id = i,
                    xPosition = (ballSize..(screenWidth - endLimit)).random(),
                    yPosition = (ballSize..(screenHeight - bottomLimit)).random(),
                    ballType = BallType.ROCK,
                    movementDirection = MovementDirection.TOP_END
                )
            )
        }

        (5..9).forEach { i ->
            balls.add(
                BallState(
                    id = i,
                    xPosition = (ballSize..(screenWidth - endLimit)).random(),
                    yPosition = (ballSize..(screenHeight - bottomLimit)).random(),
                    ballType = BallType.PAPER,
                    movementDirection = MovementDirection.TOP_END
                )
            )
        }

        (10..14).forEach { i ->
            balls.add(
                BallState(
                    id = i,
                    xPosition = (ballSize..(screenWidth - endLimit)).random(),
                    yPosition = (ballSize..(screenHeight - bottomLimit)).random(),
                    ballType = BallType.SCISSOR,
                    movementDirection = MovementDirection.TOP_END
                )
            )
        }

        _ballState.value = balls
    }
    //endregion Public Methods
}