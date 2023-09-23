package app.mat.rps.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import app.mat.rps.presentation.screen.home.HomeScreen
import app.mat.rps.presentation.screen.home.HomeViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(
            savedInstanceState
        )

        setContent {
            val homeViewModel: HomeViewModel = hiltViewModel(
                viewModelStoreOwner = this
            )

            HomeScreen(
                modifier = Modifier,
                homeViewModel = homeViewModel
            )
        }
    }
}