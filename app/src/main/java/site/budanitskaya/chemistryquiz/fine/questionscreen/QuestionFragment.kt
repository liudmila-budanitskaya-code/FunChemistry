package site.budanitskaya.chemistryquiz.fine.questionscreen

import android.animation.ObjectAnimator
import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment(), INavigate {

    private lateinit var args: QuestionFragmentArgs
    lateinit var dialog: Dialog
    var startTime: Long = 0
    val spentTimes by lazy {
        mutableListOf<Long>()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, QuestionViewModelFactory())
            .get(QuestionViewModel::class.java)
    }
    var clickFlag = false
    private var currentQuizItem = 0

    var navigateFlag = 0
    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater,
            R.layout.fragment_question, container, false
        )
        binding.game = this

        binding.viewModel = viewModel

        viewModel.shuffleQuestions()
        Timer(this).start()

        val progressbar = binding.progressBar

        val animation = ObjectAnimator.ofInt(progressbar, "progress", 1000000, 0)
        animation.duration = 30000

        animation.interpolator = LinearInterpolator()
        animation.start()

        args = QuestionFragmentArgs.fromBundle(requireArguments())
        viewModel.setQuestion(args.topic.name)

        Log.d("12345678", "onCreateView: ${viewModel.getRandomQuestionByTopic(args.topic.name)}")
        viewModel.getRandomQuestionByTopic(args.topic.name)

        binding.questionText.text = args.topic.name

        setContentView()
        binding.btnOptOne.setOnClickListener { view: View ->
            onOptionBtnClicked(view)
            createDialog()
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
        currentQuizItem++

        binding.simpleChronometer.start()
        startTime = System.currentTimeMillis()

        Log.d("onCreateView: ", "onCreateView: ${viewModel.getRowCount()}")
        return binding.root
    }


    fun onOptionBtnClicked(view: View) {
        if (!clickFlag) {
            if (view is AppCompatButton) {
                binding.btnNext.text = "Next"
                if (view.text == viewModel.currentQuestion.answers[0]) {
                    binding.bool.text = "True!"
                    binding.bool.setTextColor(getColor(requireContext(), R.color.green))
                } else {
                    binding.bool.text = "False!"
                    binding.bool.setTextColor(getColor(requireContext(), R.color.red))
                }
                binding.rationale.visibility = View.VISIBLE
                binding.rationale.text = viewModel.currentQuestion.explanation
            }
            clickFlag = true
        }
    }

    fun onNextBtnClicked() {
        viewModel.questionIncremented(args.topic.name)

        if (viewModel.questionIndex.value!! < viewModel.numQuestions) {
            moveToQuestionForward()
        } else {
            navigateToGameOverScreen()
        }
    }

    fun setContentView() {
        binding.questionText.text = viewModel.currentQuestion.text
        binding.btnOptOne.text = viewModel.answers[0]
        binding.btnOptTwo.text = viewModel.answers[1]
        binding.btnOptThree.text = viewModel.answers[2]
        binding.btnOptFour.text = viewModel.answers[3]
        binding.rationale.text = viewModel.currentQuestion.explanation
        binding.btnNext.text = "Skip"
    }

    inner class Timer(val fragment: QuestionFragment) : CountDownTimer(30000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timer.text = "seconds remaining: " + millisUntilFinished / 1000
        }

        override fun onFinish() {
            if (navigateFlag != 1) {
                fragment.navigateToGameOverScreen()
            }
        }
    }
    fun moveToQuestionForward(){
        binding.invalidateAll()
        binding.bool.text = ""
        binding.rationale.text = ""
        binding.questionText.text = viewModel.currentQuestion.text
        binding.btnOptOne.text = viewModel.answers[0]
        binding.btnOptTwo.text = viewModel.answers[1]
        binding.btnOptThree.text = viewModel.answers[2]
        binding.btnOptFour.text = viewModel.answers[3]
        clickFlag = false
        val endTime = System.currentTimeMillis()
        spentTimes.add((endTime - startTime) / 1000)
        startTime = endTime
    }

    override fun navigateToGameOverScreen() {
        navigateFlag = 1
        val endTime = System.currentTimeMillis()
        Toast.makeText(
            requireContext(),
            ((endTime - startTime) / 1000).toString(),
            Toast.LENGTH_LONG
        ).show()
        spentTimes.add((endTime - startTime) / 1000)
        startTime = endTime
        findNavController().navigate(
            QuestionFragmentDirections.actionQuestionFragmentToGameOverFragment(
                spentTimes.toLongArray()
            )
        )
    }

    fun createDialog() {
        dialog = Dialog(requireContext())
        dialog.setTitle("Player")
        dialog.setContentView(R.layout.activity_splash)
        dialog.show()

    }
}

interface INavigate {
    fun navigateToGameOverScreen()
}