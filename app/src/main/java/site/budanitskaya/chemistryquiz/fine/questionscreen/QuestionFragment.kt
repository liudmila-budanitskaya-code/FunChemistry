package site.budanitskaya.chemistryquiz.fine.questionscreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import site.budanitskaya.chemistryquiz.fine.QuizItem
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuestionBinding
import site.budanitskaya.chemistryquiz.fine.generateQuizItems

class QuestionFragment : Fragment() {

    /*private lateinit var args: QuestionFragmentArgs*/

    private val viewModel by lazy {
        ViewModelProvider(this, QuestionViewModelFactory())
            .get(QuestionViewModel::class.java)
    }


    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater, R.layout.fragment_question, container, false
        )
        binding.game = this

        binding.viewModel = viewModel

        viewModel.shuffleQuestions()


/*        args = QuestionFragmentArgs.fromBundle(requireArguments())
        binding.question.text = args.number.toString()*/

        setContentView()
        binding.btnOptOne.setOnClickListener { view: View ->
            onOptionBtnClicked(view)
        }

        binding.btnOptTwo.setOnClickListener { view: View ->
            onOptionBtnClicked(view)
        }

        binding.btnOptThree.setOnClickListener { view: View ->
            onOptionBtnClicked(view)
        }

        binding.btnOptFour.setOnClickListener { view: View ->
            onOptionBtnClicked(view)
        }


        binding.btnNext.setOnClickListener {
            onNextBtnClicked()
        }


        Log.d("onCreateView: ", "onCreateView: ${viewModel.getRowCount()}")
        return binding.root
    }


    fun onOptionBtnClicked(view: View) {
        if (view is AppCompatButton) {
            binding.btnNext.text = "Next"
            if (view.text == viewModel.currentQuestion.answers[0]) {
                binding.explanation.text = "True!"
                binding.explanation.setTextColor(resources.getColor(R.color.green))
            } else {
                binding.explanation.text = "False!"
                binding.explanation.setTextColor(resources.getColor(R.color.red))
            }
        }
    }

    fun onNextBtnClicked() {
        viewModel.questionIncremented()

        if (viewModel.questionIndex.value!! < viewModel.numQuestions) {
            viewModel.currentQuestion = viewModel.quizItems[viewModel.questionIndex.value!!]
            viewModel.setQuestion()
            binding.invalidateAll()
            setContentView()
        } else {
            findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToGameOverFragment())
        }
    }

    fun setContentView() {
        binding.questionText.text = viewModel.currentQuestion.text
        binding.btnOptOne.text = viewModel.answers[0]
        binding.btnOptTwo.text = viewModel.answers[1]
        binding.btnOptThree.text = viewModel.answers[2]
        binding.btnOptFour.text = viewModel.answers[3]
        binding.explanation.text = ""
        binding.btnNext.text = "Skip"
    }
}
