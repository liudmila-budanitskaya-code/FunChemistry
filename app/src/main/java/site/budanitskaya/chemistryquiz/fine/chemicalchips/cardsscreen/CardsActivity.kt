package site.budanitskaya.chemistryquiz.fine.chemicalchips.cardsscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import site.budanitskaya.chemistryquiz.fine.MainActivity
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.domain.Topic

class CardsActivity : AppCompatActivity() {

    private lateinit var args: CardsActivityArgs
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        args = CardsActivityArgs.fromBundle(intent.extras!!)

        fragment = CardFrontFragment()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CardFrontFragment())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        findViewById<ConstraintLayout>(R.id.container)?.setOnClickListener {
            flipCard()
        }
    }

    class CardFrontFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

        ): View {
            val view = inflater.inflate(R.layout.card_layout_front, container, false)
            val distance = 8000
            val scale = resources.displayMetrics.density * distance
            view.findViewById<ConstraintLayout>(R.id.card_view_front).cameraDistance = scale
            return view
        }
    }

    /**
     * A fragment representing the back of the card.
     */
    class CardBackFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val view = inflater.inflate(R.layout.card_layout_back, container, false)
            val distance = 8000
            val scale = resources.displayMetrics.density * distance
            view.findViewById<ConstraintLayout>(R.id.card_view_back).cameraDistance = scale
            return view
        }
    }

    private fun flipCard() {
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

        if (fragment is CardFrontFragment) {
            fragment = CardBackFragment()
            commit()

        } else {
            fragment = CardFrontFragment()
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
        val activityNavigator = ActivityNavigator( this)
        activityNavigator.navigate(
            activityNavigator.createDestination().setIntent(
                Intent(
                    this,
                    MainActivity::class.java
                )
            ), null, null, null
        )

        return super.onSupportNavigateUp()
    }
}