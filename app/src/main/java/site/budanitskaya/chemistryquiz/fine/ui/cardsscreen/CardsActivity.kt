package site.budanitskaya.chemistryquiz.fine.ui.cardsscreen

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
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
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.models.QuizItem
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.di.ServiceLocator
import site.budanitskaya.chemistryquiz.fine.domain.Topic
import site.budanitskaya.chemistryquiz.fine.domain.toQuizItem
import site.budanitskaya.chemistryquiz.fine.utils.generateRandomColor
import site.budanitskaya.chemistryquiz.fine.ui.test.TestViewModel
import site.budanitskaya.chemistryquiz.fine.ui.test.TestViewModelFactory
import site.budanitskaya.chemistryquiz.fine.ui.login.LoginActivity

class CardsActivity : AppCompatActivity() {

    val preference: SharedPreferences by lazy {
        ServiceLocator(MainApplication.applicationContext()).preferences
    }

    private lateinit var args: CardsActivityArgs
    lateinit var fragment: Fragment
    lateinit var topic: Topic
    lateinit var card: QuizItem
    var color_back: Int = 0
    var color_front: Int = 0

    private val viewModel by lazy {
        ViewModelProvider(this, TestViewModelFactory(preference))
            .get(TestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        args =
            CardsActivityArgs.fromBundle(intent.extras!!)
        topic = args.topic
        card = viewModel.getRandomQuestionByTopic(topic.name).toQuizItem()
        color_back = generateRandomColor()
        color_front = generateRandomColor()
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

/*        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }*/
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

        private val ARG_PARAM1 = "param1"
        private var param1: QuizItem? = null
        private val ARG_PARAM2 = "param2"
        private var param2: Int? = null


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.get(ARG_PARAM1) as QuizItem
                param2 = it.getInt(ARG_PARAM2)
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
            rootView.setCardBackgroundColor(param2!!)
            rootView.cameraDistance = scale
            val text = view.findViewById<TextView>(R.id.name_five)
            text.text = param1?.text
            return view
        }

        companion object {
            @JvmStatic
            fun newInstance(param1: QuizItem, param2: Int) =
                CardsFrontFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_PARAM1, param1 as Parcelable)
                        putInt(ARG_PARAM2, param2)
                    }
                }
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    class CardBackFragment : Fragment() {

        private val ARG_PARAM1 = "param1"
        private var param1: QuizItem? = null
        private val ARG_PARAM2 = "param2"
        private var param2: Int? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.get(ARG_PARAM1) as QuizItem
                param2 = it.getInt(ARG_PARAM2)
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
            linearLayout.setBackgroundColor(param2!!)
            rootView.cameraDistance = scale
            val answerTxt = view.findViewById<TextView>(R.id.answer_txt)
            answerTxt.text = param1?.answers?.get(0)!!
            val explanationTxt = view.findViewById<TextView>(R.id.explanation_txt)
            explanationTxt.text = param1!!.explanation
            return view
        }

        companion object {
            @JvmStatic
            fun newInstance(param1: QuizItem, color_back: Int) =
                CardBackFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_PARAM1, param1 as Parcelable)
                        putInt(ARG_PARAM2, color_back)
                    }
                }
        }
    }

    private fun flipCard(card: QuizItem) {
        Log.d("flipcard", "flipCard: in flip card!")
/*        if (showingBack) {
            supportFragmentManager.popBackStack()
            return
        }

        // Flip to the back.

        showingBack = true*/

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

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

            // Replace the default fragment animations with animator resources
            // representing rotations when switching to the back of the card, as
            // well as animator resources representing rotations when flipping
            // back to the front (e.g. when the system Back button is pressed).
            ?.setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )

            // Replace any fragments currently in the container view with a
            // fragment representing the next page (indicated by the
            // just-incremented currentPage variable).
            ?.replace(R.id.container, fragment)

            // Add this transaction to the back stack, allowing users to press
            // Back to get to the front of the card.
            ?.addToBackStack(null)

            // Commit the transaction.
            ?.commit()
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
        // [START auth_fui_signout]
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