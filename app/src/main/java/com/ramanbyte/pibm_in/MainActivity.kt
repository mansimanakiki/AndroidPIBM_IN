package com.ramanbyte.pibm_in

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ramanbyte.pibm_in.presentation.home.HomeScreen
import com.ramanbyte.pibm_in.ui.theme.PIBMTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle splash screen for Android 12+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            // Keep splash screen visible while loading
            splashScreen.setKeepOnScreenCondition { false }
        } else {
            // For Android 11 and below, use legacy splash with theme
            setTheme(R.style.Theme_PIBM)
        }
        
        super.onCreate(savedInstanceState)
        setContent {
            PIBMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}
