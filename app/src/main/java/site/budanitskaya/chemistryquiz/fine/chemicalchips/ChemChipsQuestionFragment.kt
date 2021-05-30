package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import site.budanitskaya.chemistryquiz.fine.R

class ChemChipsQuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chem_chips_question, container, false)
    }
}