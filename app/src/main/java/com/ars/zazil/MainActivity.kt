package com.ars.zazil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ars.zazil.View.BottomBar
import com.ars.zazil.View.Login.loginvista
import com.ars.zazil.View.Principal
import com.ars.zazil.View.TopBar
import com.ars.zazil.ui.theme.ZazilTheme
import com.ars.zazil.ui.theme.naranja

class MainActivity : ComponentActivity() {
    //ViewModel
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZazilTheme {
                loginvista()
                Scaffold (
                    topBar = {
                        Column {
                            Box (modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .background(color = naranja)
                            )
                            TopBar(modifier = Modifier)
                        }
                    },
                    bottomBar = { Column {
                        BottomBar(modifier = Modifier)
                        Box (modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = naranja)
                        )
                    }
                    }
                ) { innerPadding ->
                    Principal(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}