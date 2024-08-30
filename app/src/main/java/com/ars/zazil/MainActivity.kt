package com.ars.zazil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ars.zazil.View.Login.loginvista
import com.ars.zazil.ui.theme.ZazilTheme

class MainActivity : ComponentActivity() {
    //ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZazilTheme {
                loginvista()
            }
        }
    }
}