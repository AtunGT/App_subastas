package com.solarlyz.appsubastas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.solarlyz.appsubastas.features.navigation.NavigationMap
import com.solarlyz.appsubastas.ui.theme.AppSubastasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppSubastasTheme {
                NavigationMap()
            }
        }
    }
}