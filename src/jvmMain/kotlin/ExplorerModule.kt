import androidx.compose.ui.awt.ComposeWindow
import java.awt.FileDialog
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun importWeather(): HashMap<String, ArrayList<Float>> {
    val fd = FileDialog(ComposeWindow(), "Choose a file", FileDialog.LOAD)
    fd.setVisible(true)
    if (fd.file!=null) {
        val weather = HashMap<String, ArrayList<Float>>()
        val filename = fd.directory + fd.file

        val scanner = Scanner(File(filename))
        var firstLine = true
        val titles = ArrayList<String>()
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine().split(",")
            if (firstLine) {
                firstLine = false
                for (title in line) {
                    titles.add(title)
                    weather[title] = ArrayList()
                    weather[title]!!.add(0f)
                }
            } else {
                for ((i, v) in line.withIndex()) {
                    weather[titles[i]]!!.add(v.toFloat())
                }
            }
        }
        scanner.close()

        return weather
    }
    return HashMap<String, ArrayList<Float>>()


}
