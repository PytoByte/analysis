import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlin.random.Random


@Composable
@Preview
fun App() {
    MaterialTheme {
        Column() {
            val weather = remember { HashMap<String, ArrayList<Float>>() }
            Button(onClick = {
                weather.clear()
                weather.putAll(importWeather())
            }) {
                Text("Выбрать погоду")
            }
            DrawPolylineWithArrayList(weather)
        }
    }
}

fun getRandomHex() = Random.nextInt(255).toString(16).uppercase()

@Composable
fun DrawPolylineWithArrayList(graphs: HashMap<String, ArrayList<Float>>) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val cell = 5
        for (title in graphs.keys) {
            val mylines = Path()
            mylines.moveTo(0f, 0f);
            for (i in 0 until (graphs[title]?.size ?: 0)) {
                mylines.lineTo(i.toFloat()*cell, (graphs[title]?.get(i) ?: 0f)*cell)
            }
            val col = "FF${getRandomHex()}${getRandomHex()}${getRandomHex()}"
            drawPath(mylines, Color(col.toLong(16)), style = Stroke(width = 3f))
        }
    }
}


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
