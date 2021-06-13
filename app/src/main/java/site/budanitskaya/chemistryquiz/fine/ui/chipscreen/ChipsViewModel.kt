package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import site.budanitskaya.chemistryquiz.fine.datasource.ChipsDatasource
import site.budanitskaya.chemistryquiz.fine.domain.mapReactionEntitiesToReactions
import site.budanitskaya.chemistryquiz.fine.models.Reaction
import java.lang.StringBuilder

class ChipsViewModel : ViewModel() {
    val chipsDatasource by lazy {
        ChipsDatasource()
    }

    var valu = "1"

    private val allReactionsList = getAllReactionsList()

    var reaction = getReaction(allReactionsList)
    // и количество угаданных продуктов


    var shuffledRawProducts = reaction.answers.shuffled()


    var rawCorrectProducts = mutableListOf(reaction.correctProducts[0], reaction.correctProducts[1])
    lateinit var rawReagentsString: StringBuilder


    private var _reactionNumber = MutableLiveData(1)
    val reactionNumber: LiveData<Int>
        get() = _reactionNumber /**/

    fun setNewReaction() {

        _reactionNumber.value = _reactionNumber.value?.plus(1)

        reaction = getReaction(allReactionsList)

        shuffledRawProducts = reaction.answers.shuffled()

        rawCorrectProducts = mutableListOf(reaction.correctProducts[0], reaction.correctProducts[1])

        superFunction()


    }

    fun superFunction() {
        val rawReagentList = mutableListOf<String>()
        rawReagentList.add(reaction.reagents[0])
        rawReagentList.add(reaction.reagents[1])
        rawReagentsString = StringBuilder(rawReagentList.joinToString(" + ").plus(" = "))
    }


    fun getAllReactionsList(): List<Reaction> {
        val allReactions: List<Reaction>
        runBlocking {
            allReactions = mapReactionEntitiesToReactions(chipsDatasource.getReactionList())
        }
        return allReactions
    }

    fun getReaction(allReactions: List<Reaction>): Reaction{
        return allReactions.shuffled()[0]
    }

}