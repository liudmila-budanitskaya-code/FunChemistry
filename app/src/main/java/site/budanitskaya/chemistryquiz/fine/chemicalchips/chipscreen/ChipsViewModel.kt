package site.budanitskaya.chemistryquiz.fine.chemicalchips.chipscreen

import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.chemicalchips.ChipsDatasource
import site.budanitskaya.chemistryquiz.fine.chemicalchips.StringFormatter
import site.budanitskaya.chemistryquiz.fine.chemicalchips.generateReactionsList
import site.budanitskaya.chemistryquiz.fine.chemicalchips.mapReactionEntitiesToReactions
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionRepository
import site.budanitskaya.chemistryquiz.fine.domain.Reaction
import java.lang.StringBuilder

class ChipsViewModel: ViewModel() {

    private val allReactionsList = generateReactionsList()
    var reactionIndex = (0 until allReactionsList.size).random()

    var reaction = allReactionsList[reactionIndex]

    private val rawReagents by lazy {
        reaction.reagents
    }
    private val rawProducts = reaction.products

    lateinit var currentReaction: Reaction

    val shuffledRawProducts = rawProducts.shuffled()


    val rawCorrectProducts = mutableListOf(rawProducts[0], rawProducts[1])

    val rawFirstReagent = rawReagents[0]
    val rawSecondReagent = rawReagents[1]
    lateinit var rawReagentsString: StringBuilder
    lateinit var spannableReagentsString: SpannableString

    fun superFunction(){
        val rawReagentList = mutableListOf<String>()
        rawReagentList.add(rawFirstReagent)
        rawReagentList.add(rawSecondReagent)
        rawReagentsString = StringBuilder(rawReagentList.joinToString(" + ").plus(" = "))
        spannableReagentsString = StringFormatter.formatFormula(rawReagentsString.toString())

        val spannableCorrectProducts = mutableListOf<SpannableString>()
        rawCorrectProducts.forEach {
            spannableCorrectProducts.add(StringFormatter.formatFormula(it))
        }
    }


    val numReactions = 3

    private var _reactionNumber = MutableLiveData(0)
    val reactionNumber: LiveData<Int>
        get() = _reactionNumber

    val chipsDatasource by lazy {
        ChipsDatasource()
    }

    fun getAllReactionsList() : List<Reaction>{
        val allReactions: List<Reaction>
        runBlocking {
            allReactions = mapReactionEntitiesToReactions(chipsDatasource.getReactionList())
        }
        return allReactions
    }
}