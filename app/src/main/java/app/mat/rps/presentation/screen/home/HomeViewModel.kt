package app.mat.rps.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mat.rps.presentation.state.enum.BallType
import app.mat.rps.presentation.state.enum.MovementDirection
import app.mat.rps.presentation.state.model.BallState
import app.mat.rps.presentation.state.model.PlaygroundState
import kotlinx.coroutines.Dispatchers
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
        private val movementSteps: Int = (4 * screenDensity).toInt()
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
    private val _playgroundState: MutableStateFlow<PlaygroundState> = MutableStateFlow(
        createPlayGround()
    )

    val playgroundState: StateFlow<PlaygroundState> = _playgroundState.asStateFlow()
    //endregion Variables

    //region Private Methods
    fun startBallMovement() {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            while (true) {
                _playgroundState.value.apply {
                    launch { moveBall(rockBall1State) }.start()

                    launch { moveBall(rockBall2State) }.start()

                    launch { moveBall(rockBall3State) }.start()

                    launch { moveBall(rockBall4State) }.start()

                    launch { moveBall(rockBall5State) }.start()

                    launch { moveBall(paperBall1State) }.start()

                    launch { moveBall(paperBall2State) }.start()

                    launch { moveBall(paperBall3State) }.start()

                    launch { moveBall(paperBall4State) }.start()

                    launch { moveBall(paperBall5State) }.start()

                    launch { moveBall(scissorsBall1State) }.start()

                    launch { moveBall(scissorsBall2State) }.start()

                    launch { moveBall(scissorsBall3State) }.start()

                    launch { moveBall(scissorsBall4State) }.start()

                    launch { moveBall(scissorsBall5State) }.start()
                }

                delay(MOVEMENT_DURATION)
            }
        }
    }

    private suspend fun moveBall(
        ball: BallState?
    ) {
        ball ?: return

        val currentDirection = ball.movementDirection
        val isAtTopLimit = isAtTopLimit(
            yPosition = ball.yPosition,
            ballType = ball.type
        )
        val isAtBottomLimit = isAtBottomLimit(
            yPosition = ball.yPosition,
            ballType = ball.type
        )
        val isAtStartLimit = isAtStartLimit(
            xPosition = ball.xPosition,
            ballType = ball.type
        )
        val isAtEndLimit = isAtEndLimit(
            xPosition = ball.xPosition,
            ballType = ball.type
        )

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

    private suspend fun isAtTopLimit(
        yPosition: Int,
        ballType: BallType
    ): Boolean {
        val balls = _ballState.value
        var isAtTopLimit = false

        if (yPosition <= 0) isAtTopLimit = true
        else {
            balls.forEach { otherBall ->
                val topLimitStart = (otherBall.yPosition + ballSize) - (ballSize / 5)
                val topLimitFinish = (otherBall.yPosition + ballSize) + (ballSize / 5)

                if (yPosition in topLimitStart..topLimitFinish) {
                    if (ballType == otherBall.type) {
                        isAtTopLimit = true
                    } else {
                        judgeBall(
                            currentBallId = id,
                            currentBallType = ballType,
                            checkBallId = otherBall.id,
                            checkBallType = otherBall.type
                        )
                    }
                }
            }
        }

        return isAtTopLimit
    }

    private suspend fun isAtBottomLimit(
        yPosition: Int,
        ballType: BallType
    ): Boolean {
        val balls = _ballState.value
        var isAtBottomLimit = false

        if (yPosition >= (screenHeight - bottomLimit)) isAtBottomLimit = true
        else {
            balls.forEach { otherBall ->
                val bottomLimitStart = otherBall.yPosition - (ballSize / 5)
                val bottomLimitFinish = otherBall.yPosition + (ballSize / 5)

                if (yPosition in bottomLimitStart..bottomLimitFinish) {
                    if (ballType == otherBall.type) {
                        isAtBottomLimit = true
                    } else {
                        judgeBall(
                            currentBallId = id,
                            currentBallType = ballType,
                            checkBallId = otherBall.id,
                            checkBallType = otherBall.type
                        )
                    }
                }
            }
        }

        return isAtBottomLimit
    }

    private suspend fun isAtStartLimit(
        xPosition: Int,
        ballType: BallType
    ): Boolean {
        val balls = _ballState.value
        var isAtStartLimit = false

        if (xPosition <= 0) isAtStartLimit = true
        else {
            balls.forEach { otherBall ->
                val startLimitStart = (otherBall.xPosition + ballSize) - (ballSize / 5)
                val startLimitFinish = (otherBall.xPosition + ballSize) + (ballSize / 5)

                if (xPosition in startLimitStart..startLimitFinish) {
                    if (ballType == otherBall.type) {
                        isAtStartLimit = true
                    } else {
                        judgeBall(
                            currentBallId = id,
                            currentBallType = ballType,
                            checkBallId = otherBall.id,
                            checkBallType = otherBall.type
                        )
                    }
                }
            }
        }

        return isAtStartLimit
    }

    private suspend fun isAtEndLimit(
        xPosition: Int,
        ballType: BallType
    ): Boolean {
        val balls = _ballState.value
        var isAtEndLimit = false

        if (xPosition <= 0) isAtEndLimit = true
        else {
            balls.forEach { otherBall ->
                val endLimitStart = otherBall.xPosition - (ballSize / 5)
                val endLimitFinish = otherBall.xPosition + (ballSize / 5)

                if (xPosition in endLimitStart..endLimitFinish) {
                    if (ballType == otherBall.type) {
                        isAtEndLimit = true
                    } else {
                        judgeBall(
                            currentBallId = id,
                            currentBallType = ballType,
                            checkBallId = otherBall.id,
                            checkBallType = otherBall.type
                        )
                    }
                }
            }
        }

        return isAtEndLimit
    }

    private suspend fun judgeBall(
        currentBallId: Int,
        currentBallType: BallType,
        checkBallId: Int,
        checkBallType: BallType
    ) {
        val isWinner = isWinner(
            currentBallType = currentBallType,
            checkBallType = checkBallType
        )

        removeFromList(if (isWinner) checkBallId else currentBallId)
    }

    private suspend fun isWinner(
        currentBallType: BallType,
        checkBallType: BallType
    ): Boolean = when {
        currentBallType == BallType.ROCK && checkBallType == BallType.PAPER -> false

        currentBallType == BallType.ROCK && checkBallType == BallType.SCISSORS -> true

        currentBallType == BallType.PAPER && checkBallType == BallType.ROCK -> true

        currentBallType == BallType.PAPER && checkBallType == BallType.SCISSORS -> false

        currentBallType == BallType.SCISSORS && checkBallType == BallType.PAPER -> true

        currentBallType == BallType.SCISSORS && checkBallType == BallType.ROCK -> false

        else -> false
    }

    private fun removeFromList(
        id: Int
    ) {
        val balls = _ballState.value.toMutableList()

        if (balls.isEmpty()) return

        balls.remove(balls.find { it.id == id })

        _ballState.value = balls
    }

    private suspend fun moveBallToTop(
        ball: BallState
    ) {
        val ballList = _ballState.value.toMutableList()

        withContext(
            Dispatchers.Main
        ) {
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ball.id,
                xPosition = ball.xPosition,
                yPosition = ball.yPosition - movementSteps,
                type = ball.type,
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
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ball.id,
                xPosition = ball.xPosition,
                yPosition = ball.yPosition + movementSteps,
                type = ball.type,
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
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition,
                type = ball.type,
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
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition,
                type = ball.type,
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
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition - movementSteps,
                type = ball.type,
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
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition - movementSteps,
                type = ball.type,
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
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition + movementSteps,
                type = ball.type,
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
            val ballIndex = ballList.indexOf(ball)

            if (ballIndex == -1) return@withContext

            ballList[ballIndex] = BallState(
                id = ballIndex,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition + movementSteps,
                type = ball.type,
                movementDirection = MovementDirection.BOTTOM_START
            )

            _ballState.value = ballList
        }
    }
    //endregion Private Methods

    //region Public Methods
    fun createPlayGround(): PlaygroundState = PlaygroundState(
        rockBall1State = createBall(
            ballType = BallType.ROCK
        ),
        rockBall2State = createBall(
            ballType = BallType.ROCK
        ),
        rockBall3State = createBall(
            ballType = BallType.ROCK
        ),
        rockBall4State = createBall(
            ballType = BallType.ROCK
        ),
        rockBall5State = createBall(
            ballType = BallType.ROCK
        ),
        paperBall1State = createBall(
            ballType = BallType.ROCK
        ),
        paperBall2State = createBall(
            ballType = BallType.ROCK
        ),
        paperBall3State = createBall(
            ballType = BallType.ROCK
        ),
        paperBall4State = createBall(
            ballType = BallType.ROCK
        ),
        paperBall5State = createBall(
            ballType = BallType.ROCK
        ),
        scissorsBall1State = createBall(
            ballType = BallType.ROCK
        ),
        scissorsBall2State = createBall(
            ballType = BallType.ROCK
        ),
        scissorsBall3State = createBall(
            ballType = BallType.ROCK
        ),
        scissorsBall4State = createBall(
            ballType = BallType.ROCK
        ),
        scissorsBall5State = createBall(
            ballType = BallType.ROCK
        )
    )

    fun createBall(
        ballType: BallType
    ): BallState {
        BallState(
            xPosition = (ballSize..(screenWidth - endLimit)).random(),
            yPosition = (ballSize..(screenHeight - bottomLimit)).random(),
            type = ballType,
            movementDirection = MovementDirection.TOP_END
        )
    }
    //endregion Public Methods
}