package site.budanitskaya.chemistryquiz.fine.utils

import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.database.entities.ReactionEntity
import site.budanitskaya.chemistryquiz.fine.lists.generateQuizItems
import site.budanitskaya.chemistryquiz.fine.lists.generateReactionsList
import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import site.budanitskaya.chemistryquiz.fine.models.Reaction


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


fun mapReactionsToReactionEntities(reactions: List<Reaction>): List<ReactionEntity> {
    val reactionEntities = mutableListOf<ReactionEntity>()
    reactions.forEach {
        it.toReactionEntity()
        reactionEntities.add(it.toReactionEntity())
    }
    return reactionEntities
}

fun mapReactionEntitiesToReactions(reactionEntities: List<ReactionEntity>): MutableList<Reaction> {
    val reactions = mutableListOf<Reaction>()
    reactionEntities.forEach {
        it.toReaction()
        reactions.add(it.toReaction())
    }
    return reactions
}

fun generateReactionEntitiesList() =
    mapReactionsToReactionEntities(generateReactionsList())