package site.budanitskaya.chemistryquiz.fine.questionscreen

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat.getColor
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuestionBinding


class QuestionFragment : Fragment() {

    private lateinit var args: QuestionFragmentArgs

    private val viewModel by lazy {
        ViewModelProvider(this, QuestionViewModelFactory())
            .get(QuestionViewModel::class.java)
    }
    var clickFlag = false
    private var currentQuizItem = 0


    private lateinit var binding: FragmentQuestionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater, R.layout.fragment_question, container, false
        )
        binding.game = this

        binding.viewModel = viewModel

        viewModel.shuffleQuestions()
        var i = 0
        object : CountDownTimer(30000, 1) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timer.text = "seconds remaining: " + millisUntilFinished / 1000
                i++;
                binding.progressBar.invalidate()
                binding.progressBar.setProgress(((3000 - i*100)/(30000/1000)), true)
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_questionFragment_to_gameOverFragment)
            }
        }.start()

        args = QuestionFragmentArgs.fromBundle(requireArguments())
        viewModel.setQuestion(args.topic.name)

        Log.d("12345678", "onCreateView: ${viewModel.getFirstQuestionByTopic(args.topic.name)}")
        viewModel.getFirstQuestionByTopic(args.topic.name)

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



        Log.d("onCreateView: ", "onCreateView: ${viewModel.getRowCount()}")
        return binding.root
    }


    /*
    clickFlag должен ставиться true только в том случае, если кнопка нажата впервые и вопрос новый


     */
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

        viewModel.questionIncremented()
        if (viewModel.questionIndex.value!! < viewModel.numQuestions) {
            binding.noQuestion.invalidate()
            binding.questionText.invalidate()
            binding.btnOptOne.invalidate()
            binding.btnOptThree.invalidate()
            binding.btnOptFour.invalidate()
            binding.bool.invalidate()
            binding.rationale.invalidate()
            clickFlag = false
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
        binding.rationale.text = viewModel.currentQuestion.explanation
        binding.btnNext.text = "Skip"
    }
}
