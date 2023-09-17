package app.mat.rps.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    //region Data Classes
    enum class BallType {
        ROCK,
        PAPER,
        SCISSOR
    }

    data class BallState(
        val xPosition: Int,
        val yPosition: Int,
        val ballType: BallType
    )
    //endregion Data Classes

    //region Variables
    private val _ballState: MutableStateFlow<BallState> = MutableStateFlow(
        BallState(
            xPosition = 500,
            yPosition = 1500,
            ballType = BallType.ROCK
        )
    )

    val ballState: StateFlow<BallState> = _ballState

    private var ballJob: Job? = Job()
    //endregion Variables

    //region Private Methods
    fun startBallMovement() {
        stopBallMovement()
        ballJob = viewModelScope.launch(Dispatchers.Main) {
            while (true) {
                moveBallUp()
                delay(1000)
            }
        }
    }

    private fun stopBallMovement() {
        ballJob?.cancel()
        ballJob = null
    }

    fun moveBallUp() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition,
            yPosition = ballState.yPosition + 10,
            ballType = ballState.ballType
        )
    }

    fun moveBallDown() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition,
            yPosition = ballState.yPosition - 10,
            ballType = ballState.ballType
        )
    }

    fun moveBallRight() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition + 10,
            yPosition = ballState.yPosition,
            ballType = ballState.ballType
        )
    }

    fun moveBallLeft() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition - 10,
            yPosition = ballState.yPosition,
            ballType = ballState.ballType
        )
    }

    fun moveBallTopRight() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition + 10,
            yPosition = ballState.yPosition + 10,
            ballType = ballState.ballType
        )
    }

    fun moveBallTopLeft() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition - 10,
            yPosition = ballState.yPosition + 10,
            ballType = ballState.ballType
        )
    }

    fun moveBallBottomRight() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition + 10,
            yPosition = ballState.yPosition - 10,
            ballType = ballState.ballType
        )
    }

    fun moveBallBottomLeft() {
        val ballState = _ballState.value

        _ballState.value = BallState(
            xPosition = ballState.xPosition - 10,
            yPosition = ballState.yPosition - 10,
            ballType = ballState.ballType
        )
    }
    //endregion
}