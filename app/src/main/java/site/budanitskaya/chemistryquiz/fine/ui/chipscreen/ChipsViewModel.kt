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

    private val allReactionsList = getAllReactionsList()

    var reaction = getReaction(allReactionsList)
    // и количество угаданных продуктов
    private var rawReagents =
        reaction.reagents



    private var rawProducts = reaction.products

    lateinit var currentReaction: Reaction

    var shuffledRawProducts = rawProducts.shuffled()


    var rawCorrectProducts = mutableListOf(rawProducts[0], rawProducts[1])

    var rawFirstReagent = rawReagents[0]
    var rawSecondReagent = rawReagents[1]
    lateinit var rawReagentsString: StringBuilder


    private var _reactionNumber = MutableLiveData(1)
    val reactionNumber: LiveData<Int>
        get() = _reactionNumber /**/

    fun setNewReaction() {

        _reactionNumber.value = _reactionNumber.value?.plus(1)

        reaction = getReaction(allReactionsList)

        rawReagents = reaction.reagents

        rawProducts = reaction.products

        shuffledRawProducts = rawProducts.shuffled()

        rawCorrectProducts = mutableListOf(rawProducts[0], rawProducts[1])
        rawFirstReagent = rawReagents[0]
        rawSecondReagent = rawReagents[1]

        superFunction()


    }

    fun superFunction() {
        val rawReagentList = mutableListOf<String>()
        rawReagentList.add(rawFirstReagent)
        rawReagentList.add(rawSecondReagent)
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