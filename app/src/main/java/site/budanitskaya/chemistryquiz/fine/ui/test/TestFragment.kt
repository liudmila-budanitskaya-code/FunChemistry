package site.budanitskaya.chemistryquiz.fine.ui.test

import android.app.Dialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentTestBinding


class TestFragment : Fragment() {

    private lateinit var args: TestFragmentArgs
    lateinit var dialog: Dialog
    var startTime: Long = 0
    val spentTimes by lazy {
        mutableListOf<Long>()
    }

    val areCorrect by lazy {
        mutableListOf<Boolean>()
    }

    private val viewModel by lazy {
        ViewModelProvider(this, TestViewModelFactory())
            .get(TestViewModel::class.java)
    }
    var clickFlag = false
    private var currentQuizItem = 0

    var navigateFlag = 0
    private lateinit var binding: FragmentTestBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_test, container, false
        )
        binding.game = this

        binding.viewModel = viewModel
        binding.mCountDownTimer.start(30000)

        if(activity != null && activity is MainActivity)
            (activity as MainActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE

        viewModel.shuffleQuestions()
        Timer(this).start()

        args = TestFragmentArgs.fromBundle(
            requireArguments()
        )
        viewModel.setQuestion(args.topic.name)
        viewModel.getRandomQuestionByTopic(args.topic.name)
        binding.questionText.text = args.topic.name

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
        currentQuizItem++
        startTime = System.currentTimeMillis()
        return binding.root
    }


    fun onOptionBtnClicked(view: View) {
        if (!clickFlag) {
            if (view is RadioButton) {
                binding.btnNext.text = "Next"
                if (view.text == viewModel.currentQuestion.answers[0]) {
                    areCorrect.add(true)
                    binding.bool.text = "True!"
                    binding.bool.setTextColor(getColor(requireContext(), R.color.green))
                } else {
                    areCorrect.add(false)
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

    inner class Timer(val fragment: TestFragment) : CountDownTimer(30000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            binding.timer.text = "seconds remaining: " + millisUntilFinished / 1000
        }

        override fun onFinish() {
            if (navigateFlag != 1) {
                if(fragment.isAdded){
                    fragment.navigateToGameOverScreen()
                }
            }
        }
    }

    fun moveToQuestionForward(){
        binding.invalidateAll()
        binding.bool.text = ""
        binding.rationale.text = ""
        binding.rg.clearCheck()
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

    fun navigateToGameOverScreen() {
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
            TestFragmentDirections.actionQuestionFragmentToGameOverFragment(
                spentTimes.toLongArray(), areCorrect.toBooleanArray()
            )
        )
    }
}