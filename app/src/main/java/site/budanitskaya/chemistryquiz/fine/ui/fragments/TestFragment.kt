package site.budanitskaya.chemistryquiz.fine.ui.fragments

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
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
import dagger.hilt.android.AndroidEntryPoint
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentTestBinding
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionDatasource
import site.budanitskaya.chemistryquiz.fine.models.Topic
import site.budanitskaya.chemistryquiz.fine.lists.topics
import site.budanitskaya.chemistryquiz.fine.services.SoundService
import site.budanitskaya.chemistryquiz.fine.ui.viewmodelfactories.TestViewModelFactory
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.TestViewModel
import javax.inject.Inject

@AndroidEntryPoint
class TestFragment : Fragment() {

    private lateinit var args: TestFragmentArgs
    lateinit var dialog: Dialog
    var startTime: Long = 0
    val spentTimes by lazy {
        mutableListOf<Long>()
    }

    @Inject
    lateinit var questionDatasource: QuestionDatasource

    @Inject
    lateinit var preference: SharedPreferences


    var numOfOpenLevels = topics.filter { it.isOpen }.size

    lateinit var topic: Topic

    val areCorrect by lazy {
        mutableListOf<Boolean>()
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            TestViewModelFactory(
                preference,
                questionDatasource
            )
        ).get(TestViewModel::class.java)
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

        viewModel.shuffleQuestions()
        Timer(this).start()

        args = TestFragmentArgs.fromBundle(
            requireArguments()
        )

        topic = args.topic
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
            Log.d("addToTotalScore", "updateLevel: ${!clickFlag}")
            if (view is RadioButton) {
                binding.btnNext.text = "Next"

                topics.forEach { _ ->

                }
                if (view.text == viewModel.currentQuestion.answers[0]) {
                    if (topic.id == numOfOpenLevels) {
                        viewModel.addToTotalScore()
                    }
                    areCorrect.add(true)
                    binding.bool.text = "True!"
                    callService(R.raw.ding_sound_effect)
                    binding.bool.setTextColor(
                        getColor(
                            requireContext(),
                            R.color.material_green_a200
                        )
                    )
                } else {
                    areCorrect.add(false)
                    binding.bool.text = "False!"
                    callService(R.raw.duck_quack)
                    binding.bool.setTextColor(getColor(requireContext(), R.color.pink))
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
                if (fragment.isAdded) {
                    fragment.navigateToGameOverScreen()

                    if (topic.id == numOfOpenLevels) {
                        viewModel.subtractFromScore()
                    }
                }
            }
        }
    }

    fun moveToQuestionForward() {
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
        if (topic.id == numOfOpenLevels) {
            viewModel.addToTotalScore()
        }

        spentTimes.add((endTime - startTime) / 1000)
        startTime = endTime
        viewModel.updateLevel(topic.id)


        Log.d("navigateToGameOverScreen", "navigateToGameOverScreen: ${viewModel.totalScore.value}")
        viewModel.resetScoreToZero()
        findNavController().navigate(
            TestFragmentDirections.actionQuestionFragmentToGameOverFragment(
                spentTimes.toLongArray(), areCorrect.toBooleanArray()
            )

        )
    }

    private fun callService(rawResource: Int) {
        val serviceIntent = Intent(requireContext(), SoundService::class.java)
        serviceIntent.putExtra("soundCode", rawResource)
        requireContext().startService(serviceIntent)
    }
}