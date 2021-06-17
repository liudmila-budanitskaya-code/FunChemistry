package site.budanitskaya.chemistryquiz.fine.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ActivityNavigator
import com.firebase.ui.auth.AuthUI
import dagger.hilt.android.AndroidEntryPoint
import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.datasource.QuestionRepository
import site.budanitskaya.chemistryquiz.fine.models.Topic
import site.budanitskaya.chemistryquiz.fine.ui.viewmodelfactories.TestViewModelFactory
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.TestViewModel
import site.budanitskaya.chemistryquiz.fine.utils.extensions.toQuizItem
import site.budanitskaya.chemistryquiz.fine.utils.generateRandomColor
import javax.inject.Inject


@AndroidEntryPoint
class CardsActivity : AppCompatActivity() {

    @Inject
    lateinit var preference: SharedPreferences

    @Inject
    lateinit var questionRepository: QuestionRepository

    private lateinit var args: CardsActivityArgs
    lateinit var fragment: Fragment
    lateinit var topic: Topic
    lateinit var card: QuizItem
    var color_back: Int = 0
    var color_front: Int = 0

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            TestViewModelFactory(
                preference,
                questionRepository
            )
        ).get(TestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        populate()
        fragment = CardsFrontFragment.newInstance(card, color_front)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .commit()
        }


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<ConstraintLayout>(R.id.container)?.setOnClickListener {
            flipCard(card)
        }
        findViewById<AppCompatButton>(R.id.next_btn).setOnClickListener {
            setItem()
        }

        findViewById<AppCompatButton>(R.id.back_btn).setOnClickListener {
            returnBack()
        }
    }

    private fun populate() {
        args =
            CardsActivityArgs.fromBundle(intent.extras!!)
        topic = args.topic
        card = viewModel.getRandomQuestionByTopic(topic.name).toQuizItem()
        color_back = generateRandomColor()
        color_front = generateRandomColor()
    }

    private fun returnBack() {
        val activityNavigator = ActivityNavigator(this)
        activityNavigator.navigate(
            activityNavigator.createDestination().setIntent(
                Intent(
                    this,
                    MainActivity::class.java
                )
            ), null, null, null
        )
    }

    private fun setItem() {
        card = viewModel.getRandomQuestionByTopic(topic.name).toQuizItem()
        fragment = CardsFrontFragment.newInstance(card, color_front)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    class CardsFrontFragment : Fragment() {

        private val QUIZITEM = "quizitem"
        private var quizitem: QuizItem? = null
        private val COLOR = "param2"
        private var currentColor: Int? = null


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                quizitem = it.get(QUIZITEM) as QuizItem
                currentColor = it.getInt(COLOR)
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View {
            val view = inflater.inflate(R.layout.card_layout_front, container, false)
            val distance = 8000
            val scale = resources.displayMetrics.density * distance
            val rootView = view.findViewById<CardView>(R.id.card_view_front)
            rootView.setCardBackgroundColor(currentColor!!)
            rootView.cameraDistance = scale
            val text = view.findViewById<TextView>(R.id.name_five)
            text.text = quizitem?.text
            return view
        }

        companion object {
            @JvmStatic
            fun newInstance(quizitem: QuizItem, color: Int) =
                CardsFrontFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(QUIZITEM, quizitem as Parcelable)
                        putInt(COLOR, color)
                    }
                }
        }
    }

    class CardBackFragment : Fragment() {

        private val QUIZITEM = "quizitem"
        private var quizitem: QuizItem? = null
        private val COLOR = "param2"
        private var currentColor: Int? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                quizitem = it.get(QUIZITEM) as QuizItem
                currentColor = it.getInt(COLOR)
            }
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val view = inflater.inflate(R.layout.card_layout_back, container, false)
            val distance = 8000
            val scale = resources.displayMetrics.density * distance
            val rootView = view.findViewById<ConstraintLayout>(R.id.card_view_back)
            val linearLayout = view.findViewById<LinearLayout>(R.id.linearLayout)
            linearLayout.setBackgroundColor(currentColor!!)
            rootView.cameraDistance = scale
            val answerTxt = view.findViewById<TextView>(R.id.answer_txt)
            answerTxt.text = quizitem?.answers?.get(0)!!
            val explanationTxt = view.findViewById<TextView>(R.id.explanation_txt)
            explanationTxt.text = quizitem!!.explanation
            return view
        }

        companion object {
            @JvmStatic
            fun newInstance(param1: QuizItem, color_back: Int) =
                CardBackFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(QUIZITEM, param1 as Parcelable)
                        putInt(COLOR, color_back)
                    }
                }
        }
    }

    private fun flipCard(card: QuizItem) {

        if (fragment is CardsFrontFragment) {
            fragment = CardBackFragment.newInstance(card, color_back)
            commit()

        } else {
            fragment = CardsFrontFragment.newInstance(card, color_front)
            commit()
        }
    }

    private fun commit() {
        supportFragmentManager?.beginTransaction()
            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        returnBack()

        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.signout -> {
                signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()
                Toast.makeText(this, "Successfully Log Out", Toast.LENGTH_SHORT).show()
            }
    }
}