package site.budanitskaya.chemistryquiz.fine.questionscreen

import site.budanitskaya.chemistryquiz.fine.QuizItem
import site.budanitskaya.chemistryquiz.fine.database.Question


fun Question.toQuizItem() = QuizItem(text = questionTitle, answers = answers)

fun QuizItem.toQuestion() = Question(questionTitle = text, answers = answers)