package com.ramanbyte.pibm_in

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ramanbyte.pibm_in.presentation.home.HomeContent
import com.ramanbyte.pibm_in.presentation.home.HomeScreen
import com.ramanbyte.pibm_in.presentation.home.getSampleBanners
import com.ramanbyte.pibm_in.presentation.home.getSampleNavigationItems
import com.ramanbyte.pibm_in.presentation.home.getSamplePibmInfo
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
            setTheme(R.style.Theme_PIBM_Main)
        }

        super.onCreate(savedInstanceState)
        setContent {
            PIBMTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(), // 👈 IMPORTANT,
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Preview(
    name = "Main Screen - Light",
    showBackground = true
)
@Composable
fun MainActivityPreview() {
    PIBMTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeContent(
                banners = getSampleBanners(),
                pibmInfo = getSamplePibmInfo(),
                navigationItems = getSampleNavigationItems()
            )
        }
    }
}

