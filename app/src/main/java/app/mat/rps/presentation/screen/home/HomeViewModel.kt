package app.mat.rps.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mat.rps.presentation.state.model.BallState
import app.mat.rps.presentation.state.type.BallType
import app.mat.rps.presentation.state.type.MovementDirection
import app.mat.rps.presentation.state.type.MovementLimitation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class HomeViewModel : ViewModel() {
    //region Companion object
    companion object {
        private var screenWidth: Int = 0
        private var screenHeight: Int = 0
        private var screenDensity: Float = 1f
        private val ballSize: Int = (50 * screenDensity).toInt()
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
    private val _rock1State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock1State: StateFlow<BallState> = _rock1State

    private val _rock2State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock2State: StateFlow<BallState> = _rock2State

    private val _rock3State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock3State: StateFlow<BallState> = _rock3State

    private val _rock4State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock4State: StateFlow<BallState> = _rock4State

    private val _rock5State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock5State: StateFlow<BallState> = _rock5State

    private val _paper1State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper1State: StateFlow<BallState> = _paper1State

    private val _paper2State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper2State: StateFlow<BallState> = _paper2State

    private val _paper3State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper3State: StateFlow<BallState> = _paper3State

    private val _paper4State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper4State: StateFlow<BallState> = _paper4State

    private val _paper5State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper5State: StateFlow<BallState> = _paper5State

    private val _scissors1State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors1State: StateFlow<BallState> = _scissors1State

    private val _scissors2State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors2State: StateFlow<BallState> = _scissors2State

    private val _scissors3State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors3State: StateFlow<BallState> = _scissors3State

    private val _scissors4State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors4State: StateFlow<BallState> = _scissors4State

    private val _scissors5State: MutableStateFlow<BallState> = MutableStateFlow(
        getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors5State: StateFlow<BallState> = _scissors5State
    //endregion Variables

    //region Private Methods
    fun start() {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            while (true) {
                val rock1State = _rock1State.value

                if (rock1State.id != 0) {
                    initiateBallMovement(
                        ballState = rock1State
                    )
                }

                val rock2State = _rock2State.value

                if (rock2State.id != 0) {
                    initiateBallMovement(
                        ballState = rock2State
                    )
                }

                val rock3State = _rock3State.value

                if (rock3State.id != 0) {
                    initiateBallMovement(
                        ballState = rock3State
                    )
                }

                val rock4State = _rock4State.value

                if (rock4State.id != 0) {
                    initiateBallMovement(
                        ballState = rock4State
                    )
                }

                val rock5State = _rock5State.value

                if (rock5State.id != 0) {
                    initiateBallMovement(
                        ballState = rock5State
                    )
                }

                val paper1State = _paper1State.value

                if (paper1State.id != 0) {
                    initiateBallMovement(
                        ballState = paper1State
                    )
                }

                val paper2State = _paper2State.value

                if (paper2State.id != 0) {
                    initiateBallMovement(
                        ballState = paper2State
                    )
                }

                val paper3State = _paper3State.value

                if (paper3State.id != 0) {
                    initiateBallMovement(
                        ballState = paper3State
                    )
                }

                val paper4State = _paper4State.value

                if (paper4State.id != 0) {
                    initiateBallMovement(
                        ballState = paper4State
                    )
                }

                val paper5State = _paper5State.value

                if (paper5State.id != 0) {
                    initiateBallMovement(
                        ballState = paper5State
                    )
                }

                val scissors1State = _scissors1State.value

                if (paper1State.id != 0) {
                    initiateBallMovement(
                        ballState = scissors1State
                    )
                }

                val scissors2State = _scissors2State.value

                if (scissors2State.id != 0) {
                    initiateBallMovement(
                        ballState = scissors2State
                    )
                }

                val scissors3State = _scissors3State.value

                if (scissors3State.id != 0) {
                    initiateBallMovement(
                        ballState = scissors3State
                    )
                }

                val scissors4State = _scissors4State.value

                if (scissors4State.id != 0) {
                    initiateBallMovement(
                        ballState = scissors4State
                    )
                }

                val scissors5State = _scissors5State.value

                if (scissors5State.id != 0) {
                    initiateBallMovement(
                        ballState = scissors5State
                    )
                }

                delay(MOVEMENT_DURATION)
            }
        }.start()
    }

    private suspend fun initiateBallMovement(
        ballState: BallState?
    ) {
        ballState?.let {
            viewModelScope.async(
                context = Dispatchers.IO
            ) {
                moveBall(
                    ballState
                )
            }.start()
        }
    }

    private suspend fun moveBall(
        ballState: BallState
    ) {
        calculateBallMovement(
            ballState = ballState
        )

        checkBallContact(
            ballState = ballState
        )
    }

    private suspend fun calculateBallMovement(
        ballState: BallState
    ) {
        val currentDirection = ballState.movementDirection

        val movementLimitation = getMovementLimitations(
            ballState = ballState
        )

        when (movementLimitation) {
            MovementLimitation.TOP -> {
                when (currentDirection) {
                    MovementDirection.TOP_START, MovementDirection.START -> moveBallToBottomStart(
                        ball = ballState
                    )

                    MovementDirection.TOP_END, MovementDirection.END -> moveBallToBottomEnd(
                        ball = ballState
                    )

                    else -> moveBallToBottom(
                        ball = ballState
                    )
                }
            }

            MovementLimitation.BOTTOM -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM_START, MovementDirection.START -> moveBallToTopStart(
                        ball = ballState
                    )

                    MovementDirection.BOTTOM_END, MovementDirection.END -> moveBallToTopEnd(
                        ball = ballState
                    )

                    else -> moveBallToTop(
                        ballState
                    )
                }
            }

            MovementLimitation.START -> {
                when (currentDirection) {
                    MovementDirection.TOP_START, MovementDirection.TOP -> moveBallToTopEnd(
                        ball = ballState
                    )

                    MovementDirection.BOTTOM_START, MovementDirection.BOTTOM -> moveBallToBottomEnd(
                        ball = ballState
                    )

                    else -> moveBallToEnd(
                        ball = ballState
                    )
                }
            }

            MovementLimitation.END -> {
                when (currentDirection) {
                    MovementDirection.TOP_END, MovementDirection.TOP -> moveBallToTopStart(
                        ball = ballState
                    )

                    MovementDirection.BOTTOM_END, MovementDirection.BOTTOM -> moveBallToBottomStart(
                        ball = ballState
                    )

                    else -> moveBallToStart(
                        ball = ballState
                    )
                }
            }


            MovementLimitation.START_TOP -> {
                when (currentDirection) {
                    MovementDirection.TOP, MovementDirection.TOP_END -> moveBallToEnd(
                        ball = ballState
                    )

                    MovementDirection.START, MovementDirection.BOTTOM_START -> moveBallToBottom(
                        ball = ballState
                    )

                    else -> moveBallToBottomEnd(
                        ball = ballState
                    )
                }
            }

            MovementLimitation.TOP_END -> {
                when (currentDirection) {
                    MovementDirection.TOP, MovementDirection.TOP_START -> moveBallToStart(
                        ball = ballState
                    )

                    MovementDirection.END, MovementDirection.BOTTOM_END -> moveBallToBottom(
                        ball = ballState
                    )

                    else -> moveBallToBottomStart(
                        ball = ballState
                    )
                }
            }

            MovementLimitation.BOTTOM_START -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM, MovementDirection.BOTTOM_END -> moveBallToEnd(
                        ball = ballState
                    )

                    MovementDirection.START, MovementDirection.TOP_START -> moveBallToTop(
                        ball = ballState
                    )

                    else -> moveBallToTopEnd(
                        ball = ballState
                    )
                }
            }

            MovementLimitation.END_BOTTOM -> {
                when (currentDirection) {
                    MovementDirection.BOTTOM, MovementDirection.BOTTOM_END -> moveBallToStart(
                        ball = ballState
                    )

                    MovementDirection.END, MovementDirection.TOP_END -> moveBallToTop(
                        ball = ballState
                    )

                    else -> moveBallToTopStart(
                        ball = ballState
                    )
                }
            }

            MovementLimitation.NONE -> {
                when (currentDirection) {
                    MovementDirection.TOP_START -> moveBallToTopStart(
                        ball = ballState
                    )

                    MovementDirection.BOTTOM_START -> moveBallToBottomStart(
                        ball = ballState
                    )

                    MovementDirection.TOP_END -> moveBallToTopEnd(
                        ball = ballState
                    )

                    MovementDirection.BOTTOM_END -> moveBallToBottomEnd(
                        ball = ballState
                    )

                    MovementDirection.TOP -> moveBallToTop(
                        ball = ballState
                    )

                    MovementDirection.BOTTOM -> moveBallToBottom(
                        ball = ballState
                    )

                    MovementDirection.START -> moveBallToStart(
                        ball = ballState
                    )

                    MovementDirection.END -> moveBallToEnd(
                        ball = ballState
                    )
                }
            }
        }
    }

    private fun checkBallContact(
        ballState: BallState
    ) {
        val rock1State = _rock1State.value

        if (rock1State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = rock1State
            )
        }

        val rock2State = _rock2State.value

        if (rock2State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = rock2State
            )
        }

        val rock3State = _rock3State.value

        if (rock3State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = rock3State
            )
        }

        val rock4State = _rock4State.value

        if (rock4State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = rock4State
            )
        }

        val rock5State = _rock5State.value

        if (rock5State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = rock5State
            )
        }

        val paper1State = _paper1State.value

        if (paper1State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = paper1State
            )
        }

        val paper2State = _paper2State.value

        if (paper2State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = paper2State
            )
        }

        val paper3State = _paper3State.value

        if (paper3State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = paper3State
            )
        }

        val paper4State = _paper4State.value

        if (paper4State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = paper4State
            )
        }

        val paper5State = _paper5State.value

        if (paper5State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = paper5State
            )
        }

        val scissors1State = _scissors1State.value

        if (paper1State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = paper1State
            )
        }

        val scissors2State = _scissors2State.value

        if (scissors2State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = scissors2State
            )
        }

        val scissors3State = _scissors3State.value

        if (scissors3State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = scissors3State
            )
        }

        val scissors4State = _scissors4State.value

        if (scissors4State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = scissors4State
            )
        }

        val scissors5State = _scissors5State.value

        if (scissors5State.id != 0) {
            initiateCalculateBallContact(
                currentBallState = ballState,
                checkBallState = scissors5State
            )
        }
    }

    private fun initiateCalculateBallContact(
        currentBallState: BallState,
        checkBallState: BallState
    ) {
        viewModelScope.launch(
            context = Dispatchers.IO
        ) {
            calculateBallContact(
                currentBallState = currentBallState,
                checkBallState = checkBallState
            )
        }.start()
    }

    private fun calculateBallContact(
        currentBallState: BallState,
        checkBallState: BallState
    ) {
        if (currentBallState.id == checkBallState.id) return

        val distance = calculateDistanceBetween(
            currentBallState = currentBallState,
            checkBallState = checkBallState
        )

        if (distance < ballSize) {
            if (currentBallState.type == checkBallState.type) {
                updateBallState(
                    ballId = currentBallState.id,
                    ballState = BallState(
                        id = currentBallState.id,
                        xPosition = currentBallState.xPosition,
                        yPosition = currentBallState.yPosition,
                        type = currentBallState.type,
                        movementDirection = getOppositeDirection(
                            currentBallState.movementDirection
                        )
                    )
                )

                updateBallState(
                    ballId = checkBallState.id,
                    ballState = BallState(
                        id = checkBallState.id,
                        xPosition = checkBallState.xPosition,
                        yPosition = checkBallState.yPosition,
                        type = checkBallState.type,
                        movementDirection = getOppositeDirection(
                            checkBallState.movementDirection
                        )
                    )
                )
            } else {
                val isWinner = isWinner(
                    currentBallType = currentBallState.type,
                    checkBallType = checkBallState.type
                )

                if (isWinner) updateBallState(
                    ballId = checkBallState.id,
                    ballState = null
                ) else updateBallState(
                    ballId = currentBallState.id,
                    ballState = null
                )
            }
        }
    }

    private fun getOppositeDirection(
        movementDirection: MovementDirection
    ): MovementDirection = when (movementDirection) {
        MovementDirection.TOP_START -> MovementDirection.BOTTOM_END
        MovementDirection.BOTTOM_START -> MovementDirection.TOP_END
        MovementDirection.TOP_END -> MovementDirection.BOTTOM_START
        MovementDirection.BOTTOM_END -> MovementDirection.TOP_START
        MovementDirection.TOP -> MovementDirection.BOTTOM
        MovementDirection.BOTTOM -> MovementDirection.TOP
        MovementDirection.START -> MovementDirection.END
        MovementDirection.END -> MovementDirection.START
    }

    private fun getMovementLimitations(
        ballState: BallState
    ): MovementLimitation {
        val movementLimitation: MovementLimitation

        val isTopLimited = ballState.yPosition < 0
        val isBottomLimited = (ballState.yPosition + ballSize) > screenHeight
        val isStartLimited = ballState.xPosition < 0
        val isEndLimited = (ballState.yPosition + ballSize) > screenHeight

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

    private fun calculateDistanceBetween(
        currentBallState: BallState,
        checkBallState: BallState
    ): Int {
        val currentCentralX = currentBallState.xPosition + (ballSize / 2)
        val checkCentralX = checkBallState.xPosition + (ballSize / 2)
        val currentCentralY = currentBallState.yPosition + (ballSize / 2)
        val checkCentralY = checkBallState.yPosition + (ballSize / 2)
        val xDiff: Double = (checkCentralX - currentCentralX).toDouble()
        val yDiff: Double = (checkCentralY - currentCentralY).toDouble()
        return abs(sqrt((xDiff.pow(2.0) + yDiff.pow(2.0)))).toInt()
    }

    private fun isWinner(
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

    private fun updateBallState(
        ballId: Int,
        ballState: BallState?
    ) = when (ballId) {
        1 -> _rock1State.value = ballState ?: getDefaultBallState(
            ballType = BallType.ROCK
        )

        2 -> _rock2State.value = ballState ?: getDefaultBallState(
            ballType = BallType.ROCK
        )

        3 -> _rock3State.value = ballState ?: getDefaultBallState(
            ballType = BallType.ROCK
        )

        4 -> _rock4State.value = ballState ?: getDefaultBallState(
            ballType = BallType.ROCK
        )

        5 -> _rock5State.value = ballState ?: getDefaultBallState(
            ballType = BallType.ROCK
        )

        6 -> _paper1State.value = ballState ?: getDefaultBallState(
            ballType = BallType.PAPER
        )

        7 -> _paper2State.value = ballState ?: getDefaultBallState(
            ballType = BallType.PAPER
        )

        8 -> _paper3State.value = ballState ?: getDefaultBallState(
            ballType = BallType.PAPER
        )

        9 -> _paper4State.value = ballState ?: getDefaultBallState(
            ballType = BallType.PAPER
        )

        10 -> _paper5State.value = ballState ?: getDefaultBallState(
            ballType = BallType.PAPER
        )

        11 -> _scissors1State.value = ballState ?: getDefaultBallState(
            ballType = BallType.SCISSORS
        )

        12 -> _scissors2State.value = ballState ?: getDefaultBallState(
            ballType = BallType.SCISSORS
        )

        13 -> _scissors3State.value = ballState ?: getDefaultBallState(
            ballType = BallType.SCISSORS
        )

        14 -> _scissors4State.value = ballState ?: getDefaultBallState(
            ballType = BallType.SCISSORS
        )

        15 -> _scissors5State.value = ballState ?: getDefaultBallState(
            ballType = BallType.SCISSORS
        )

        else -> {}
    }

    private suspend fun moveBallToTop(
        ball: BallState
    ) = withContext(
        Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition,
                yPosition = ball.yPosition - movementSteps,
                type = ball.type,
                movementDirection = MovementDirection.TOP
            )
        )
    }

    private suspend fun moveBallToBottom(
        ball: BallState
    ) = withContext(
        context = Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition,
                yPosition = ball.yPosition + movementSteps,
                type = ball.type,
                movementDirection = MovementDirection.BOTTOM
            )
        )
    }

    private suspend fun moveBallToEnd(
        ball: BallState
    ) = withContext(
        Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition,
                type = ball.type,
                movementDirection = MovementDirection.END
            )
        )
    }

    private suspend fun moveBallToStart(
        ball: BallState
    ) = withContext(
        context = Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition,
                type = ball.type,
                movementDirection = MovementDirection.START
            )
        )
    }

    private suspend fun moveBallToTopEnd(
        ball: BallState
    ) = withContext(
        context = Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition - movementSteps,
                type = ball.type,
                movementDirection = MovementDirection.TOP_END
            )
        )
    }

    private suspend fun moveBallToTopStart(
        ball: BallState
    ) = withContext(
        context = Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition - movementSteps,
                type = ball.type,
                movementDirection = MovementDirection.TOP_START
            )
        )
    }

    private suspend fun moveBallToBottomEnd(
        ball: BallState
    ) = withContext(
        context = Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition + movementSteps,
                yPosition = ball.yPosition + movementSteps,
                type = ball.type,
                movementDirection = MovementDirection.BOTTOM_END
            )
        )
    }

    private suspend fun moveBallToBottomStart(
        ball: BallState
    ) = withContext(
        context = Dispatchers.Main
    ) {
        updateBallState(
            ballId = ball.id,
            ballState = BallState(
                id = ball.id,
                xPosition = ball.xPosition - movementSteps,
                yPosition = ball.yPosition + movementSteps,
                type = ball.type,
                movementDirection = MovementDirection.BOTTOM_START
            )
        )
    }

    private fun createBall(
        id: Int,
        ballType: BallType
    ): BallState = BallState(
        id = id,
        xPosition = (ballSize..(screenWidth - ballSize)).random(),
        yPosition = (ballSize..(screenHeight - ballSize)).random(),
        type = ballType,
        movementDirection = MovementDirection.entries.shuffled().first()
    )

    private fun getDefaultBallState(
        ballType: BallType
    ) = BallState(
        id = 0,
        xPosition = 0,
        yPosition = 0,
        type = ballType,
        movementDirection = MovementDirection.BOTTOM
    )
    //endregion Private Methods

    //region Public Methods
    fun createPlayGround() {
        _rock1State.value = createBall(
            id = 1,
            ballType = BallType.ROCK
        )

        _rock2State.value = createBall(
            id = 2,
            ballType = BallType.ROCK
        )

        _rock3State.value = createBall(
            id = 3,
            ballType = BallType.ROCK
        )

        _rock4State.value = createBall(
            id = 4,
            ballType = BallType.ROCK
        )

        _rock5State.value = createBall(
            id = 5,
            ballType = BallType.ROCK
        )

        _paper1State.value = createBall(
            id = 6,
            ballType = BallType.PAPER
        )

        _paper2State.value = createBall(
            id = 7,
            ballType = BallType.PAPER
        )

        _paper3State.value = createBall(
            id = 8,
            ballType = BallType.PAPER
        )

        _paper4State.value = createBall(
            id = 9,
            ballType = BallType.PAPER
        )

        _paper5State.value = createBall(
            id = 10,
            ballType = BallType.PAPER
        )

        _scissors1State.value = createBall(
            id = 11,
            ballType = BallType.SCISSORS
        )

        _scissors2State.value = createBall(
            id = 12,
            ballType = BallType.SCISSORS
        )

        _scissors3State.value = createBall(
            id = 13,
            ballType = BallType.SCISSORS
        )

        _scissors4State.value = createBall(
            id = 14,
            ballType = BallType.SCISSORS
        )

        _scissors5State.value = createBall(
            id = 15,
            ballType = BallType.SCISSORS
        )
    }
//endregion Public Methods
}