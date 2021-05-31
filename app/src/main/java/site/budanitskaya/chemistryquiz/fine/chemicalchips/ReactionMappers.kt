package site.budanitskaya.chemistryquiz.fine.chemicalchips

import site.budanitskaya.chemistryquiz.fine.QuizItem
import site.budanitskaya.chemistryquiz.fine.database.Question
import site.budanitskaya.chemistryquiz.fine.generateQuizItems

fun ReactionEntity.toReaction() = Reaction(reagents = reagents, products = products)

fun Reaction.toReactionEntity() = ReactionEntity(reagents = reagents, products = products)

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