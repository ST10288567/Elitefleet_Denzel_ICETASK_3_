package com.example.elitefleetmanagement

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.elitefleetmanagement.navigation.AppNavigation
import com.example.elitefleetmanagement.ui.theme.EliteFleetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EliteFleetTheme {
                AppNavigation()
            }
        }
    }
}