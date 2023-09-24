package app.mat.rps.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mat.rps.common.MovementHandler
import app.mat.rps.common.util.controller.Controller
import app.mat.rps.common.util.engine.Engine
import app.mat.rps.presentation.state.model.BallState
import app.mat.rps.presentation.state.type.BallType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val engine: Engine,
    private val controller: Controller
) : ViewModel(), MovementHandler {
    //region Companion Object
    companion object {
        const val MOVEMENT_DURATION = 8
    }
    //endregion

    //region Variables
    private val _rock1State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock1State: StateFlow<BallState> = _rock1State

    private val _rock2State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock2State: StateFlow<BallState> = _rock2State

    private val _rock3State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock3State: StateFlow<BallState> = _rock3State

    private val _rock4State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock4State: StateFlow<BallState> = _rock4State

    private val _rock5State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.ROCK
        )
    )
    val rock5State: StateFlow<BallState> = _rock5State

    private val _paper1State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper1State: StateFlow<BallState> = _paper1State

    private val _paper2State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper2State: StateFlow<BallState> = _paper2State

    private val _paper3State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper3State: StateFlow<BallState> = _paper3State

    private val _paper4State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper4State: StateFlow<BallState> = _paper4State

    private val _paper5State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.PAPER
        )
    )
    val paper5State: StateFlow<BallState> = _paper5State

    private val _scissors1State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors1State: StateFlow<BallState> = _scissors1State

    private val _scissors2State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors2State: StateFlow<BallState> = _scissors2State

    private val _scissors3State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors3State: StateFlow<BallState> = _scissors3State

    private val _scissors4State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors4State: StateFlow<BallState> = _scissors4State

    private val _scissors5State: MutableStateFlow<BallState> = MutableStateFlow(
        engine.getDefaultBallState(
            ballType = BallType.SCISSORS
        )
    )
    val scissors5State: StateFlow<BallState> = _scissors5State
    //endregion Variables

    //region Override Methods
    override suspend fun moveBall(
        ballState: BallState
    ) = updateBallState(
        ballId = ballState.id,
        ballState = ballState
    )
    //endregion Override Methods

    //region Public Methods
    fun start() = viewModelScope.launch(
        Dispatchers.IO
    ) {
        async {
            while (true) {
                handleBallMovement()

                delay(
                    engine.getMovementDuration()
                )
            }
        }.start()

        async {
            while (true) {
                handleEncounters()

                delay(
                    engine.getEncounterCheckDuration()
                )
            }
        }.start()
    }

    fun getEngine(): Engine = engine
    //endregion Public Methods

    //region Private Methods
    private suspend fun handleBallMovement() = viewModelScope.launch {
        val rock1State = _rock1State.value

        if (rock1State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = rock1State
                )
            }.start()
        }

        val rock2State = _rock2State.value

        if (rock2State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = rock2State
                )
            }.start()
        }

        val rock3State = _rock3State.value
        if (rock3State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = rock3State
                )
            }.start()
        }

        val rock4State = _rock4State.value

        if (rock4State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = rock4State
                )
            }.start()
        }

        val rock5State = _rock5State.value

        if (rock5State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = rock5State
                )
            }.start()
        }

        val paper1State = _paper1State.value

        if (paper1State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = paper1State
                )
            }.start()
        }

        val paper2State = _paper2State.value

        if (paper2State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = paper2State
                )
            }.start()
        }

        val paper3State = _paper3State.value

        if (paper3State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = paper3State
                )
            }.start()
        }

        val paper4State = _paper4State.value

        if (paper4State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = paper4State
                )
            }.start()
        }

        val paper5State = _paper5State.value

        if (paper5State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = paper5State
                )
            }.start()
        }

        val scissors1State = _scissors1State.value

        if (scissors1State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = scissors1State
                )
            }.start()
        }

        val scissors2State = _scissors2State.value

        if (scissors2State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = scissors2State
                )
            }.start()
        }

        val scissors3State = _scissors3State.value

        if (scissors3State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = scissors3State
                )
            }.start()
        }

        val scissors4State = _scissors4State.value

        if (scissors4State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = scissors4State
                )
            }.start()
        }

        val scissors5State = _scissors5State.value

        if (scissors5State.id != 0) {
            async {
                engine.calculateBallMovement(
                    ballState = scissors5State
                )
            }.start()
        }
    }

    private suspend fun handleEncounters() {
        val encounterList = controller.getEncounters(
            ballStateList = getBallStateList(),
            ballSize = engine.getBallSize(),
            ballEncounterRadius = engine.getBallEncounterRadius()
        )

        encounterList.forEach {
            if (it.third == controller.getDefaultBallId()) {
                it.first.apply {
                    updateBallState(
                        ballId = id,
                        ballState = BallState(
                            id = id,
                            xPosition = xPosition,
                            yPosition = yPosition,
                            type = type,
                            movementDirection = controller.getMirrorDirection(
                                movementDirection,
                                it.second.movementDirection
                            )
                        )
                    )
                }

                it.second.apply {
                    updateBallState(
                        ballId = id,
                        ballState = BallState(
                            id = id,
                            xPosition = xPosition,
                            yPosition = yPosition,
                            type = type,
                            movementDirection = controller.getMirrorDirection(
                                movementDirection,
                                it.first.movementDirection
                            )
                        )
                    )
                }
            } else {
                updateBallState(
                    ballId = it.third ?: 0,
                    ballState = null
                )
            }
        }
    }

    private suspend fun updateBallState(
        ballId: Int,
        ballState: BallState?
    ) = withContext(
        context = Dispatchers.Main
    ) {
        when (ballId) {
            1 -> _rock1State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.ROCK
            )

            2 -> _rock2State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.ROCK
            )

            3 -> _rock3State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.ROCK
            )

            4 -> _rock4State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.ROCK
            )

            5 -> _rock5State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.ROCK
            )

            6 -> _paper1State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.PAPER
            )

            7 -> _paper2State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.PAPER
            )

            8 -> _paper3State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.PAPER
            )

            9 -> _paper4State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.PAPER
            )

            10 -> _paper5State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.PAPER
            )

            11 -> _scissors1State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.SCISSORS
            )

            12 -> _scissors2State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.SCISSORS
            )

            13 -> _scissors3State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.SCISSORS
            )

            14 -> _scissors4State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.SCISSORS
            )

            15 -> _scissors5State.value = ballState ?: engine.getDefaultBallState(
                ballType = BallType.SCISSORS
            )

            else -> {}
        }
    }

    private fun getBallStateList(): List<BallState> {
        val ballStateList: MutableList<BallState> = mutableListOf()

        val rock1State = _rock1State.value

        if (rock1State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                rock1State
            )
        }

        val rock2State = _rock2State.value

        if (rock2State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                rock2State
            )
        }

        val rock3State = _rock3State.value

        if (rock3State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                rock3State
            )
        }

        val rock4State = _rock4State.value

        if (rock4State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                rock4State
            )
        }

        val rock5State = _rock5State.value

        if (rock5State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                rock5State
            )
        }

        val paper1State = _paper1State.value

        if (paper1State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                paper1State
            )
        }

        val paper2State = _paper2State.value

        if (paper2State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                paper2State
            )
        }

        val paper3State = _paper3State.value

        if (paper3State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                paper3State
            )
        }

        val paper4State = _paper4State.value

        if (paper4State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                paper4State
            )
        }

        val paper5State = _paper5State.value

        if (paper5State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                paper5State
            )
        }

        val scissors1State = _scissors1State.value

        if (scissors1State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                scissors1State
            )
        }

        val scissors2State = _scissors2State.value

        if (scissors2State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                scissors2State
            )
        }

        val scissors3State = _scissors3State.value

        if (scissors3State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                scissors3State
            )
        }

        val scissors4State = _scissors4State.value

        if (scissors4State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                scissors4State
            )
        }

        val scissors5State = _scissors5State.value

        if (scissors5State.id != controller.getDefaultBallId()) {
            ballStateList.add(
                scissors5State
            )
        }

        return ballStateList
    }
//endregion Private Methods

    //region Public Methods
    fun setScreenMeasures(
        width: Int,
        height: Int,
        pixelDensity: Float
    ) = engine.init(
        movementHandler = this,
        width = width,
        height = height,
        pixelDensity = pixelDensity
    )

    fun createPlayGround() {
        _rock1State.value = engine.createBallState(
            id = 1,
            ballType = BallType.ROCK
        )

        _rock2State.value = engine.createBallState(
            id = 2,
            ballType = BallType.ROCK
        )

        _rock3State.value = engine.createBallState(
            id = 3,
            ballType = BallType.ROCK
        )

        _rock4State.value = engine.createBallState(
            id = 4,
            ballType = BallType.ROCK
        )

        _rock5State.value = engine.createBallState(
            id = 5,
            ballType = BallType.ROCK
        )

        _paper1State.value = engine.createBallState(
            id = 6,
            ballType = BallType.PAPER
        )

        _paper2State.value = engine.createBallState(
            id = 7,
            ballType = BallType.PAPER
        )

        _paper3State.value = engine.createBallState(
            id = 8,
            ballType = BallType.PAPER
        )

        _paper4State.value = engine.createBallState(
            id = 9,
            ballType = BallType.PAPER
        )

        _paper5State.value = engine.createBallState(
            id = 10,
            ballType = BallType.PAPER
        )

        _scissors1State.value = engine.createBallState(
            id = 11,
            ballType = BallType.SCISSORS
        )

        _scissors2State.value = engine.createBallState(
            id = 12,
            ballType = BallType.SCISSORS
        )

        _scissors3State.value = engine.createBallState(
            id = 13,
            ballType = BallType.SCISSORS
        )

        _scissors4State.value = engine.createBallState(
            id = 14,
            ballType = BallType.SCISSORS
        )

        _scissors5State.value = engine.createBallState(
            id = 15,
            ballType = BallType.SCISSORS
        )
    }
//endregion Public Methods
}