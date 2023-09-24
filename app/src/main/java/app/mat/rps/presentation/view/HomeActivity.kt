package app.mat.rps.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import app.mat.rps.presentation.screen.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        setContent {
            HomeScreen(
                modifier = Modifier
            )
        }
    }
}