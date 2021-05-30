package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.questionscreen.QuestionFragmentArgs
import java.lang.Exception

class ChemChipsQuestionFragment : Fragment() {

    private lateinit var args: ChemChipsQuestionFragmentArgs
    private lateinit var fragmentView: View
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
                fragmentView.findViewById<TextView>(R.id.text_game).text = "Chemical chips"
            }
            else -> throw  Exception()
        }
        return fragmentView

    }
}