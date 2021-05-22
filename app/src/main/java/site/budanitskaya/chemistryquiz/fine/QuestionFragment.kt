package site.budanitskaya.chemistryquiz.fine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    private lateinit var args: QuestionFragmentArgs

    private val questions: MutableList<QuizItem> = mutableListOf(
        QuizItem(
            text = "What is Android Jetpack?",
            answers = listOf("All of these", "Tools", "Documentation", "Libraries")
        ),
        QuizItem(
            text = "What is the base class for layouts?",
            answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")
        ),
        QuizItem(
            text = "What layout do you use for complex screens?",
            answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")
        ),
        QuizItem(
            text = "What do you use to push structured data into a layout?",
            answers = listOf("Data binding", "Data pushing", "Set text", "An OnClick method")
        ),
        QuizItem(
            text = "What method do you use to inflate layouts in fragments?",
            answers = listOf(
                "onCreateView()",
                "onActivityCreated()",
                "onCreateLayout()",
                "onInflateLayout()"
            )
        ),
        QuizItem(
            text = "What's the build system for Android?",
            answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")
        ),
        QuizItem(
            text = "Which class do you use to create a vector drawable?",
            answers = listOf(
                "VectorDrawable",
                "AndroidVectorDrawable",
                "DrawableVector",
                "AndroidVector"
            )
        ),
        QuizItem(
            text = "Which one of these is an Android navigation component?",
            answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")
        ),
        QuizItem(
            text = "Which XML element lets you register an activity with the launcher activity?",
            answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")
        ),
        QuizItem(
            text = "What do you use to mark a layout for data binding?",
            answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>")
        )
    )

    lateinit var currentQuestion: QuizItem
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 3)
    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater, R.layout.fragment_question, container, false
        )

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
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

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
    }

    fun onOptionBtnClicked(view: View) {
        if (view is AppCompatButton) {
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
            currentQuestion = questions[questionIndex]
            setQuestion()
            binding.invalidateAll()
            setContentView()
        } else {
            findNavController().navigate(QuestionFragmentDirections.actionQuestionFragmentToGameOverFragment())
        }
    }

    fun setContentView() {
        binding.questionText.text = currentQuestion.text
        binding.btnOptOne.text = answers[0]
        binding.btnOptTwo.text = answers[1]
        binding.btnOptThree.text = answers[2]
        binding.btnOptFour.text = answers[3]
        binding.explanation.text = ""
    }
}