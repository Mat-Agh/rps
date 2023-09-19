package app.mat.rps.presentation.state.model

data class PlaygroundState(
    val rockBall1State: BallState?,
    val rockBall2State: BallState?,
    val rockBall3State: BallState?,
    val rockBall4State: BallState?,
    val rockBall5State: BallState?,
    val paperBall1State: BallState?,
    val paperBall2State: BallState?,
    val paperBall3State: BallState?,
    val paperBall4State: BallState?,
    val paperBall5State: BallState?,
    val scissorsBall1State: BallState?,
    val scissorsBall2State: BallState?,
    val scissorsBall3State: BallState?,
    val scissorsBall4State: BallState?,
    val scissorsBall5State: BallState?,
)
