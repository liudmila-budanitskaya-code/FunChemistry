package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.chip.Chip
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.QuizItem
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.chemicalchips.StringFormatter.Companion.formatFormula
import site.budanitskaya.chemistryquiz.fine.database.QuestionDatabase.Companion.getInstance
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentChemChipsQuestionBinding
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuestionBinding
import java.lang.StringBuilder

class ChemChipsQuestionFragment : Fragment() {

    lateinit var currentReaction: Reaction
    val numReactions = 3

    private var _reactionNumber = MutableLiveData(0)
    val reactionNumber: LiveData<Int>
        get() = _reactionNumber

    val db = getInstance(MainApplication.getApplication()!!)

    private val allReactionsList = generateReactionsList()

    var reactionIndex = (0 until allReactionsList.size).random()

    var reaction = allReactionsList[reactionIndex]

    private val rawReagents by lazy {
        reaction.reagents
    }
    private lateinit var binding: FragmentChemChipsQuestionBinding

    lateinit var chipHashMap: Map<Chip, String>
    private lateinit var rawReagentsString: StringBuilder

    private val rawProducts = reaction.products

    private val shuffledRawProducts = rawProducts.shuffled()


    private val rawCorrectProducts = mutableListOf(rawProducts[0], rawProducts[1])

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_chem_chips_question, container, false
        )

        binding.textGame.text =
            "What is the most appropriate products for this chemical reaction?"

// raw - unprocessed html string
        // spannable - formula with indices
        val rawFirstReagent = rawReagents[0]
        val rawSecondReagent = rawReagents[1]

        chipHashMap = hashMapOf(
            binding.chipOne to shuffledRawProducts[0],
            binding.chipTwo to shuffledRawProducts[1],
            binding.chipThree to shuffledRawProducts[2],
            binding.chipFour to shuffledRawProducts[3],
            binding.chipFive to shuffledRawProducts[4]

        )

        Log.d("creative tag", "onCreateView: ${db.reactionDao()?.getReactionList()?.size}")

        val rawReagentList = mutableListOf<String>()
        rawReagentList.add(rawFirstReagent)
        rawReagentList.add(rawSecondReagent)
        rawReagentsString = StringBuilder(rawReagentList.joinToString(" + ").plus(" = "))
        val spannableReagentsString = formatFormula(rawReagentsString.toString())

        val spannableCorrectProducts = mutableListOf<SpannableString>()
        rawCorrectProducts.forEach {
            spannableCorrectProducts.add(formatFormula(it))
        }
        with(binding){
            chipOne.text = formatFormula(shuffledRawProducts[0])
            chipTwo.text = formatFormula(shuffledRawProducts[1])
            chipThree.text = formatFormula(shuffledRawProducts[2])
            chipFour.text = formatFormula(shuffledRawProducts[3])
            chipFive.text = formatFormula(shuffledRawProducts[4])
            txtChemReaction.setText(spannableReagentsString, TextView.BufferType.SPANNABLE)
            chipOne.setOnClickListener { view ->
                onChipClick(view)
            }

            chipTwo.setOnClickListener { view ->
                onChipClick(view)
            }
            chipThree.setOnClickListener { view ->
                onChipClick(view)
            }
            chipFour.setOnClickListener { view ->
                onChipClick(view)
            }
            chipFive.setOnClickListener { view ->
                onChipClick(view)
            }
        }

        return binding.root
    }

    fun onChipClick(view: View) {
        if (view is Chip) {
            val values: List<String> = chipHashMap.filterKeys { it == view }.values.toList()
            Log.d("onCreateView", "onCreateView: $values")
            if (rawCorrectProducts.contains(values[0]) && rawCorrectProducts.size > 1) {
                view.animate().translationY(300F).alpha(
                    0.0F
                ).duration = 3000
                rawReagentsString.append(("${values[0]} + "))
                with(binding){
                    txtChemReaction.invalidate()
                    txtChemReaction.animate().rotation(360F)
                    txtChemReaction.setText(
                        formatFormula(rawReagentsString.toString()),
                        TextView.BufferType.SPANNABLE
                    )
                }
                rawCorrectProducts.remove(values[0])
            } else if (rawCorrectProducts.contains(values[0]) && rawCorrectProducts.size == 1) {
                view.animate().translationY(-3000F).alpha(
                    0.0F
                ).duration = 300
                rawReagentsString.append("${values[0]}")
                with(binding){
                    txtChemReaction.invalidate()
                    txtChemReaction.animate().rotation(-360F)
                    txtChemReaction.setText(
                        formatFormula(rawReagentsString.toString()),
                        TextView.BufferType.SPANNABLE
                    )
                    txtChemReaction.animate().translationY(16000F).translationX(3000F).alpha(
                        0.0F
                    ).duration = 6000
                }
                rawCorrectProducts.remove(values[0])
            }
        }
    }
}