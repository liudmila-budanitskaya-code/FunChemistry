package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.chemicalchips.StringFormatter.Companion.formatFormula

class ChemChipsQuestionFragment : Fragment() {

    private lateinit var args: ChemChipsQuestionFragmentArgs
    private lateinit var fragmentView: View
    private val reagents =
        mutableListOf("Ba(OH)<sub>2</sub>", "H<sub>2</sub>SO<sub size = 2>4</sub>")
    private val products = mutableListOf("BaSO<sub>4</sub>", "H<sub>2</sub>O")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        args = ChemChipsQuestionFragmentArgs.fromBundle(requireArguments())

        when (args.gameName.name) {
            "Chemical chips" -> {
                fragmentView =
                    inflater.inflate(R.layout.fragment_chem_chips_question, container, false)
                fragmentView.findViewById<TextView>(R.id.text_game).text =
                    "What is the most appropriate products for this chemical reaction?"
            }
            else -> throw  Exception()
        }

        val first_reagent = reagents[0]
        val second_reagent = reagents[1]
        val first_product = products[0]
        val second_product = products[1]

        val reagentList = mutableListOf<String>()

        reagentList.add(first_reagent)
        reagentList.add(second_reagent)

        val rawReagentsString = reagentList.joinToString(" + ").plus(" = ")
        val formattedReagentsString = formatFormula(rawReagentsString)

        val correctAnswers = mutableListOf(first_product, second_product)
        val answers = mutableListOf<String>()
        correctAnswers.forEach {
            answers.add(formatFormula(it).toString())
        }

        val chipOne = fragmentView.findViewById<Chip>(R.id.chip_one)
        val chipTwo = fragmentView.findViewById<Chip>(R.id.chip_two)
        val chipThree = fragmentView.findViewById<Chip>(R.id.chip_three)
        val chipFour = fragmentView.findViewById<Chip>(R.id.chip_four)
        val chipFive = fragmentView.findViewById<Chip>(R.id.chip_five)

        chipOne.text = formatFormula(first_product)
        chipTwo.text = formatFormula(second_product)
        chipThree.text = formatFormula("BaS")
        chipFour.text = formatFormula("BaSO<sub>3</sub>")
        chipFive.text = formatFormula("BaSO<sub>2</sub>")
        val chemReaction: TextView = fragmentView.findViewById<TextView>(R.id.chem_reaction)
        chemReaction.setText(formattedReagentsString, TextView.BufferType.SPANNABLE)

        chipOne.setOnClickListener { view ->
            Log.d("onCreateView", "onCreateView: ${((view as Chip).text)}")
            Log.d("onCreateView", "onCreateView: ${formatFormula(first_product).toString()}")
            Log.d("onCreateView", "onCreateView: $answers")
            Log.d("onCreateView", "onCreateView: ${(view as Chip).text}")
            Log.d(
                "onCreateView",
                "onCreateView: ${(answers.contains((view as Chip).text.toString()))}"
            )
            if (answers.contains(((view as Chip).text.toString()))) {
                val str = view.text
                view.animate().translationY(300F).alpha(
                    0.0F
                ).duration = 3000
                chemReaction.text = formatFormula(rawReagentsString.plus(str))
            }
        }

        chipTwo.setOnClickListener {

        }
        chipThree.setOnClickListener {

        }
        chipFour.setOnClickListener {

        }
        chipFive.setOnClickListener {

        }

        return fragmentView

    }
}