package site.budanitskaya.chemistryquiz.fine.utils

import android.content.Context
import android.graphics.Color
import site.budanitskaya.chemistryquiz.fine.MainApplication
import java.util.*

fun colorWrapper(res: Int, context: Context): Int = context.resources.getColor(res)

fun generateRandomColor(): Int {
    val rnd = Random()
    return Color.argb(120, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}
fun resourceWrapper(res: Int): Array<String> =
    MainApplication.applicationContext().resources.getStringArray(res)