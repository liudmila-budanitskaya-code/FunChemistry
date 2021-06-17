package site.budanitskaya.chemistryquiz.fine.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.chemicalchips.games
import site.budanitskaya.chemistryquiz.fine.dialogs.chooseDifficultyDialog
import site.budanitskaya.chemistryquiz.fine.ui.adapters.GamesListAdapter

class GameListFragment : Fragment() {

    val adapter: GamesListAdapter by lazy {
        GamesListAdapter(games) {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()
            when(it.name){
                "Chemical chips" -> {
                    chooseDifficultyDialog(this)
                }
                "Chemical crossword" -> {
                    findNavController().navigate(
                        R.id.action_navigation_game_to_crosswordFragment
                    )
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_gameslist, container, false)
        val gameRecycler = view.findViewById<RecyclerView>(R.id.game_recycler)
        gameRecycler.adapter = adapter
        gameRecycler.layoutManager = GridLayoutManager(requireContext(), 1)
        return view
    }

    fun navigateToChipsScreen(number: Int){
        findNavController().navigate(
            GameListFragmentDirections.actionNavigationGameToChemChipsQuestionFragment(
                number
            )
        )
    }
}