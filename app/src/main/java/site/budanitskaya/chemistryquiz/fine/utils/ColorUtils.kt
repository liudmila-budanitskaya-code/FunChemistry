package site.budanitskaya.chemistryquiz.fine.utils

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import site.budanitskaya.chemistryquiz.fine.MainApplication
import java.util.*

fun colorWrapper(res: Int, context: Context): Int = context.resources.getColor(res)

fun generateRandomColor(): Int {
    return Color.argb((100..256).random(), (0..256).random(), (0..256).random(), (0..256).random())
}

fun paintString(string: String): SpannableString {
    val text = SpannableString(string)

    for (i in string.indices) {
        if (i < string.length) {
            text.setSpan(ForegroundColorSpan(generateRandomColor()), i, i + 1, 0)
        }
    }
    return text
}

fun resourceWrapper(res: Int): Array<String> =
    MainApplication.applicationContext().resources.getStringArray(res)