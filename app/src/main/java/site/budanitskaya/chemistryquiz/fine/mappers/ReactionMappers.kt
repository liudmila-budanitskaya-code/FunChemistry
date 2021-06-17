package site.budanitskaya.chemistryquiz.fine.domain

import site.budanitskaya.chemistryquiz.fine.database.entities.ReactionEntity
import site.budanitskaya.chemistryquiz.fine.lists.generateReactionsList

fun ReactionEntity.toReaction() = Reaction(reagents = reagents, answers = products, correctProducts = products.subList(0, 2))

fun Reaction.toReactionEntity() = ReactionEntity(reagents = reagents, products = answers)

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