package site.budanitskaya.chemistryquiz.fine.models

import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.lists.generateQuizItems


fun Question.toQuizItem() = QuizItem(text = questionTitle, answers = answers, topic = topic, explanation = explanation)

fun QuizItem.toQuestion() = Question(questionTitle = text, answers = answers,  topic = topic, explanation = explanation)

fun mapQuizItemsToQuestionsList(quizItems: List<QuizItem>): List<Question> {
    val questions = mutableListOf<Question>()
    quizItems.forEach {
        it.toQuestion()
        questions.add(it.toQuestion())
    }
    return questions
}

fun mapQuestionsToQuizItems(questions: List<Question>): MutableList<QuizItem> {
    val quizItems = mutableListOf<QuizItem>()
    questions.forEach {
        it.toQuizItem()
        quizItems.add(it.toQuizItem())
    }
    return quizItems
}

fun generateQuestionsList() =
    mapQuizItemsToQuestionsList(generateQuizItems())
