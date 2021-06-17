package site.budanitskaya.chemistryquiz.fine.utils

import site.budanitskaya.chemistryquiz.fine.database.entities.Question
import site.budanitskaya.chemistryquiz.fine.database.entities.ReactionEntity
import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import site.budanitskaya.chemistryquiz.fine.models.Reaction
import site.budanitskaya.chemistryquiz.fine.utils.StringFormatter

fun Question.toQuizItem() = QuizItem(text = questionTitle, answers = answers, topic = topic, explanation = explanation)

fun QuizItem.toQuestion() = Question(questionTitle = text, answers = answers,  topic = topic, explanation = explanation)

fun ReactionEntity.toReaction() = Reaction(reagents = reagents, answers = products, correctProducts = products.subList(0, 2))

fun Reaction.toReactionEntity() = ReactionEntity(reagents = reagents, products = answers)

fun List<String>.toSpannableList(): List<String>{
    val list: MutableList<String> = mutableListOf()
    for (i in this){
        list.add(StringFormatter.formatFormula(i).toString())
    }
    return list
}