package site.budanitskaya.chemistryquiz.fine.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.lists.games
import site.budanitskaya.chemistryquiz.fine.dialogs.chooseDifficultyDialog
import site.budanitskaya.chemistryquiz.fine.ui.adapters.GamesListAdapter


@AndroidEntryPoint
class GameListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_gameslist, container, false)
        val gameRecycler = view.findViewById<RecyclerView>(R.id.game_recycler)

        val adapter =
            GamesListAdapter(games) {
                when (it.name) {
                    "Chemical chips" -> {
                        chooseDifficultyDialog(this)
                    }
                    "Chemical crossword" -> {
                        navigateToCrosswordScreen(view)
                    }
                }
            }


        gameRecycler.adapter = adapter
        gameRecycler.layoutManager = GridLayoutManager(requireContext(), 1)
        return view
    }

    private fun navigateToCrosswordScreen(view: View) {
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        CoroutineScope(Dispatchers.Main).launch {
            findNavController().navigate(
                R.id.action_navigation_game_to_crosswordFragment
            )
        }
        progressBar.visibility = View.VISIBLE
    }

    fun navigateToChipsScreen(view: View, number: Int) {
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        findNavController().navigate(
            GameListFragmentDirections.actionNavigationGameToChemChipsQuestionFragment(
                number
            )
        )
        progressBar.visibility = View.VISIBLE
    }
}