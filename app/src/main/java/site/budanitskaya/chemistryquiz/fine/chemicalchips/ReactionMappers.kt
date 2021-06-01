package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.text.SpannableString
import site.budanitskaya.chemistryquiz.fine.chemicalchips.StringFormatter.Companion.formatFormula
import site.budanitskaya.chemistryquiz.fine.domain.Reaction

fun ReactionEntity.toReaction() = Reaction(reagents = reagents, products = products)

fun Reaction.toReactionEntity() = ReactionEntity(reagents = reagents, products = products)

fun String.toSpannableString() = formatFormula(this)

fun List<String>.toSpannableListString(): List<SpannableString> {
    val spannableStringList = mutableListOf<SpannableString>()
    for (i in this) {
        spannableStringList.add(formatFormula(i))
    }
    return spannableStringList
}

fun List<ReactionEntity>.toSpannableReactionsList() : List<SpannableReaction>{
    val spannableReactionsList = mutableListOf<SpannableReaction>()
    for (i in this){
        spannableReactionsList.add(i.toSpannableReaction())
    }
    return spannableReactionsList
}

fun ReactionEntity.toSpannableReaction() =
    SpannableReaction(
        reagents = reagents.toSpannableListString(),
        products = products.toSpannableListString()
    )

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