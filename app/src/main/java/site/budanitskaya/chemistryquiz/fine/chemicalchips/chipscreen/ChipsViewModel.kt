package site.budanitskaya.chemistryquiz.fine.chemicalchips.chipscreen

import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import site.budanitskaya.chemistryquiz.fine.chemicalchips.ChipsDatasource
import site.budanitskaya.chemistryquiz.fine.chemicalchips.StringFormatter
import site.budanitskaya.chemistryquiz.fine.chemicalchips.mapReactionEntitiesToReactions
import site.budanitskaya.chemistryquiz.fine.domain.Reaction
import java.lang.StringBuilder

class ChipsViewModel : ViewModel() {
    val chipsDatasource by lazy {
        ChipsDatasource()
    }

    private var allReactionsList : List<Reaction>

    init {
        val allReactions: List<Reaction>
        runBlocking {
            allReactions = mapReactionEntitiesToReactions(chipsDatasource.getReactionList())
        }
        allReactionsList = allReactions
    }

    var shuffledReactionsList: List<Reaction>

    init {
        lateinit var reaction: Reaction
        val mixedReactions = mutableListOf<Reaction>()
        allReactionsList.forEach {
            val products = it.products.shuffled()
            val reagents = it.reagents.shuffled()
            reaction = Reaction(reagents, products)
            mixedReactions.add(reaction)
        }


        shuffledReactionsList = mixedReactions.shuffled()
    }


    var reactionIndex = 0

    lateinit var rawReagentsString: StringBuilder

    fun superFunction() {
        val rawReagentList = mutableListOf<String>()
        rawReagentList.add(shuffledReactionsList[reactionIndex].reagents[0])
        rawReagentList.add(shuffledReactionsList[reactionIndex].reagents[1])
        rawReagentsString = StringBuilder(rawReagentList.joinToString(" + ").plus(" = "))
    }


    val numReactions = 3

    private var _reactionNumber = MutableLiveData(0)
    val reactionNumber: LiveData<Int>
        get() = _reactionNumber

}