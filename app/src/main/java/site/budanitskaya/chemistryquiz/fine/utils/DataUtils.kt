package site.budanitskaya.chemistryquiz.fine.utils

import android.content.Context
import android.graphics.Color
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.models.toQuestion
import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import java.util.*

fun resourceWrapper(res: Int): Array<String> =
    MainApplication.applicationContext().resources.getStringArray(res)

fun generateRandomColor(): Int {
    val rnd = Random()
    return Color.argb(120, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}

fun mapFromSpannableListToList(quizItems: List<QuizItem>): List<Question> {
    val questions = mutableListOf<Question>()
    quizItems.forEach {
        it.toQuestion()
        questions.add(it.toQuestion())
    }
    return questions
}

fun List<String>.toSpannableList(): List<String>{
    val list: MutableList<String> = mutableListOf()
    for (i in this){
        list.add(StringFormatter.formatFormula(i).toString())
    }
    return list
}

fun colorWrapper(res: Int, context: Context): Int = context.resources.getColor(res)
