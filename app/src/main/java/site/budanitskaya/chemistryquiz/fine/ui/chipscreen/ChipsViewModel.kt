package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import site.budanitskaya.chemistryquiz.fine.datasource.ChipsDatasource
import site.budanitskaya.chemistryquiz.fine.models.mapReactionEntitiesToReactions
import site.budanitskaya.chemistryquiz.fine.models.Reaction
import java.lang.StringBuilder

class ChipsViewModel(private val chipsDatasource: ChipsDatasource) : ViewModel() {

    private var _numOfGuessedReactions = MutableLiveData(0)
    val numOfGuessedReactions: LiveData<Int>
        get() = _numOfGuessedReactions

    private var _isReactionGuessed = MutableLiveData(false)
    val isReactionGuessed: LiveData<Boolean>
        get() = _isReactionGuessed

    fun guessReaction(){
        _numOfGuessedReactions.value = _numOfGuessedReactions.value?.plus(1)
    }

    var valu = "1"

    fun setReactionGuessed(){
        _isReactionGuessed.value = true
    }

    fun setReactionUnGuessed(){
        _isReactionGuessed.value = false
    }

    private val allReactionsList = getAllReactionsList()

    var reaction = getReaction(allReactionsList)

    private var _numOfGuessedProducts = MutableLiveData(0)
    val numOfGuessedProducts: LiveData<Int>
        get() = _numOfGuessedProducts

    var shuffledRawProducts = reaction.answers.shuffled()

    lateinit var rawReagentsString: StringBuilder


    fun setNewReaction() {

        reaction = getReaction(allReactionsList)

        shuffledRawProducts = reaction.answers.shuffled()

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

    fun getReaction(allReactions: List<Reaction>): Reaction {
        return allReactions.shuffled()[0]
    }

    fun guessProduct(){
        _numOfGuessedProducts.value = _numOfGuessedProducts.value?.plus(1)
    }

    fun unGuessProduct(){
        _numOfGuessedProducts.value = 0
    }

}