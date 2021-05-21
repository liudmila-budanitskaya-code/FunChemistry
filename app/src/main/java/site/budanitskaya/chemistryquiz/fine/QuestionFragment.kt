package site.budanitskaya.chemistryquiz.fine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class QuestionFragment : Fragment() {

    private lateinit var args: QuestionFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        args = QuestionFragmentArgs.fromBundle(requireArguments())
        val view = inflater.inflate(R.layout.fragment_question, container, false)
        view.findViewById<TextView>(R.id.question).text = args.number.toString()
        return view
    }
}