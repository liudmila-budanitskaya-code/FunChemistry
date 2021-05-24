package site.budanitskaya.chemistryquiz.fine.questionscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
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

        viewModel.shuffleQuestions()

        binding.game = this

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
        viewModel.questionIndex++
        if (viewModel.questionIndex < viewModel.numQuestions) {
            viewModel.currentQuestion = viewModel.quizItems[viewModel.questionIndex]
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


/*
class QuestionFragment : Fragment() {

    /*private lateinit var args: site.budanitskaya.chemistryquiz.fine.QuestionFragmentArgs*/


    private val question by lazy {
        Question()
    }

    private lateinit var viewModel: QuestionViewModel


    val questions by lazy {
        viewModel.questions.value?.toMutableList()
    }

    lateinit var currentQuestion: QuizItem
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 3
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

        viewModel = ViewModelProvider(requireActivity(), QuestionViewModelFactory())
            .get(QuestionViewModel::class.java)


        viewModel.insertQuestions(viewModel.getQuestionsToPut(generateQuizItems()))

        currentQuestion = questions!![0].toQuizItem()


        binding.questionText.text = currentQuestion.text
        binding.btnOptOne.text = answers[0]
        binding.btnOptTwo.text = answers[1]
        binding.btnOptThree.text = answers[2]
        binding.btnOptFour.text = answers[3]
        binding.explanation.text = ""
        binding.btnNext.text = "Skip"

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

        Log.d("onCreateView", "onCreateView: ${viewModel.getQuestionList().value}")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        shuffleQuestions()
    }

    private fun shuffleQuestions() {
        questions?.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        if (questions != null) {
            currentQuestion = questions!![questionIndex].toQuizItem()
        }
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
    }

    fun onOptionBtnClicked(view: View) {
        if (view is AppCompatButton) {
            binding.btnNext.text = "Next"
            if (view.text == currentQuestion.answers[0]) {
                binding.explanation.text = "True!"
                binding.explanation.setTextColor(resources.getColor(R.color.green))
            } else {
                binding.explanation.text = "False!"
                binding.explanation.setTextColor(resources.getColor(R.color.red))
            }
        }
    }

    fun onNextBtnClicked() {
        questionIndex++
        if (questionIndex < numQuestions) {
            currentQuestion = questions?.get(questionIndex)?.toQuizItem()!!
            setQuestion()
            binding.invalidateAll()

            binding.questionText.text = currentQuestion.text
            binding.btnOptOne.text = answers[0]
            binding.btnOptTwo.text = answers[1]
            binding.btnOptThree.text = answers[2]
            binding.btnOptFour.text = answers[3]
            binding.explanation.text = ""
            binding.btnNext.text = "Skip"

        } else {
            /*findNavController().navigate(site.budanitskaya.chemistryquiz.fine.QuestionFragmentDirections.actionQuestionFragmentToGameOverFragment())*/
        }
    }


} */
