package site.budanitskaya.chemistryquiz.fine.utils

import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import java.lang.StringBuilder

fun buildCardText(quizItem: QuizItem): String {
    val cardText = StringBuilder()
    cardText.append(quizItem.text)
    val shuffledAnswers = quizItem.answers.shuffled()
    for(ans in shuffledAnswers){
        cardText.append("\n - $ans")
    }
    return cardText.toString()
}