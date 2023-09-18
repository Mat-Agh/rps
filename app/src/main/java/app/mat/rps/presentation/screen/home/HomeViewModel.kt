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
        private var screenWidth: Int = 0
        private var screenHeight: Int = 0
        private var screenDensity: Float = 1f
        private val ballSize: Int = (70 * screenDensity).toInt()
        private val endLimit = (ballSize * 2)
        private val bottomLimit = (ballSize * 2) + ((ballSize * 2) / 3)
        private val movementSpeed: Int = (1 * screenDensity).toInt()
        const val MOVEMENT_DURATION: Long = 1

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
        val id: Int,
        val xPosition: Int,
        val yPosition: Int,
        val ballType: BallType,
        val movementDirection: MovementDirection
    )
    //endregion Data Classes

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

    private suspend fun moveBallToTop(
        ball: BallState
    ) {
        val ballState = _ballState.value

        withContext(
            Dispatchers.Main
        ) {
            _ballState.value = BallState(
                xPosition = ballState.xPosition,
                yPosition = ballState.yPosition - movementSpeed,
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
                yPosition = ballState.yPosition + movementSpeed,
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
                xPosition = ballState.xPosition + movementSpeed,
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
                xPosition = ballState.xPosition - movementSpeed,
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
                xPosition = ballState.xPosition + movementSpeed,
                yPosition = ballState.yPosition - movementSpeed,
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
                xPosition = ballState.xPosition - movementSpeed,
                yPosition = ballState.yPosition - movementSpeed,
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
                xPosition = ballState.xPosition + movementSpeed,
                yPosition = ballState.yPosition + movementSpeed,
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
                xPosition = ballState.xPosition - movementSpeed,
                yPosition = ballState.yPosition + movementSpeed,
                ballType = ballState.ballType,
                movementDirection = MovementDirection.BOTTOM_START
            )
        }
    }

    private fun getBalls(): List<BallState> {


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

    //endregion Private Methods
}