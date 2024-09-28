package com.mags.pruebas.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.util.Locale
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Calendario(modifier:Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)
        .padding(0.dp)
        .background(color = MaterialTheme.colorScheme.primary)){

        val fechaActual = LocalDate.now()
        val primerDiaDelMes = fechaActual.withDayOfMonth(1)
        val diaDel_primerDiaDelMes = primerDiaDelMes.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es", "ES"))
        val mesActual = fechaActual.month.getDisplayName(TextStyle.FULL, Locale("es", "ES"))

        //Imprime el primer dia del mes actual, ejemplo: Saabdo 1 de septiembre del 2024
        //usando -print
        println("${diaDel_primerDiaDelMes} ${primerDiaDelMes} de ${mesActual} del ${fechaActual.year}")



        Text(
            text = "${mesActual}",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(10.dp)
        )
        Row {
            val semana = listOf("Dom","Lun","Mar","Mie","Jue","Vie","Sab")
            var index = 0

            repeat(7){
                Box{
                    Text(
                        text = semana[index],
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .width(45.dp)
                            .height(30.dp)
                    )
                }
                index++
            }
        }

        // Variables y valores para el conteo de días en el mes
        val startDay = 1
        var dayIndex = 1
        var dayCount = 1
        var startCount = false
        var sumDay:Int = 0

        if(diaDel_primerDiaDelMes=="domingo"){
            sumDay = 1
            println(sumDay)
        }else if(diaDel_primerDiaDelMes=="lunes"){
            sumDay = 2
        }else if(diaDel_primerDiaDelMes=="martes"){
            sumDay = 3
        }else if(diaDel_primerDiaDelMes=="miercoles"){
            sumDay = 4
        }else if(diaDel_primerDiaDelMes=="jueves"){
            sumDay = 5
        }else if(diaDel_primerDiaDelMes=="viernes"){
            sumDay = 6
        }else if(diaDel_primerDiaDelMes=="sabado"){
            sumDay = 7
        }



        //imprime el sumday
        println(sumDay)

        val lastDay = fechaActual.with(TemporalAdjusters.lastDayOfMonth()).dayOfMonth
        var today = ""
        var mod = Modifier
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .width(45.dp)
            .height(45.dp)


        repeat(6) {
            Row {
                repeat(7) {
                    dayIndex++

                    if(dayIndex >= startDay + sumDay) {
                        startCount = true
                    }
                    if (startCount) {
                        today = dayCount.toString()
                        dayCount++
                        mod = mod
                            .clickable { }
                    }

                    if (dayIndex <= lastDay+sumDay) {
                        Box(modifier = mod) {
                            Text(
                                text = today,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}