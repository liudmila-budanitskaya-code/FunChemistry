package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.os.Bundle
import android.text.SpannableString
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.chemicalchips.StringFormatter.Companion.formatFormula
import java.lang.StringBuilder

class ChemChipsQuestionFragment : Fragment() {

    private val allReactionsList = generateReactionsList()

    var reactionIndex = (0 until allReactionsList.size).random()

    var reaction = allReactionsList[reactionIndex]

    private lateinit var fragmentView: View
    private val rawReagents by lazy {
        reaction.reagents
    }

    lateinit var chipHashMap: Map<Chip, String>
    private lateinit var rawReagentsString: StringBuilder

    private val rawProducts = reaction.products

    private val shuffledRawProducts = rawProducts.shuffled()
    private lateinit var txtChemReaction: TextView


    private val rawCorrectProducts = mutableListOf(rawProducts[0], rawProducts[1])

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentView =
            inflater.inflate(R.layout.fragment_chem_chips_question, container, false)
        fragmentView.findViewById<TextView>(R.id.text_game).text =
            "What is the most appropriate products for this chemical reaction?"

// raw - unprocessed html string
        // spannable - formula with indices
        val rawFirstReagent = rawReagents[0]
        val rawSecondReagent = rawReagents[1]

        val chipOne = fragmentView.findViewById<Chip>(R.id.chip_one)
        val chipTwo = fragmentView.findViewById<Chip>(R.id.chip_two)
        val chipThree = fragmentView.findViewById<Chip>(R.id.chip_three)
        val chipFour = fragmentView.findViewById<Chip>(R.id.chip_four)
        val chipFive = fragmentView.findViewById<Chip>(R.id.chip_five)

        chipHashMap = hashMapOf(
            chipOne to shuffledRawProducts[0],
            chipTwo to shuffledRawProducts[1],
            chipThree to shuffledRawProducts[2],
            chipFour to shuffledRawProducts[3],
            chipFive to shuffledRawProducts[4]
        )

        val rawReagentList = mutableListOf<String>()
        rawReagentList.add(rawFirstReagent)
        rawReagentList.add(rawSecondReagent)
        rawReagentsString = StringBuilder(rawReagentList.joinToString(" + ").plus(" = "))
        val spannableReagentsString = formatFormula(rawReagentsString.toString())

        val spannableCorrectProducts = mutableListOf<SpannableString>()
        rawCorrectProducts.forEach {
            spannableCorrectProducts.add(formatFormula(it))
        }

        chipOne.text = formatFormula(shuffledRawProducts[0])
        chipTwo.text = formatFormula(shuffledRawProducts[1])
        chipThree.text = formatFormula(shuffledRawProducts[2])
        chipFour.text = formatFormula(shuffledRawProducts[3])
        chipFive.text = formatFormula(shuffledRawProducts[4])

        txtChemReaction = fragmentView.findViewById(R.id.txt_chem_reaction)
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

        return fragmentView

    }

    fun onChipClick(view: View) {
        if (view is Chip) {
            val values: List<String> = chipHashMap.filterKeys { it == view }.values.toList()
            Log.d("onCreateView", "onCreateView: $values")
            if (rawCorrectProducts.contains(values[0]) && rawCorrectProducts.size == 2) {
                view.animate().translationY(300F).alpha(
                    0.0F
                ).duration = 3000
                rawReagentsString.append(("${values[0]} + "))
                txtChemReaction.invalidate()
                txtChemReaction.animate().rotation(360F)
                txtChemReaction.setText(
                    formatFormula(rawReagentsString.toString()),
                    TextView.BufferType.SPANNABLE
                )
                rawCorrectProducts.remove(values[0])
            } else if (rawCorrectProducts.contains(values[0]) && rawCorrectProducts.size == 1) {
                view.animate().translationY(-3000F).alpha(
                    0.0F
                ).duration = 300
                rawReagentsString.append("${values[0]}")
                txtChemReaction.invalidate()
                txtChemReaction.animate().rotation(-360F)
                txtChemReaction.setText(
                    formatFormula(rawReagentsString.toString()),
                    TextView.BufferType.SPANNABLE
                )
                rawCorrectProducts.remove(values[0])
            }
        }
    }
}