package site.budanitskaya.chemistryquiz.fine.chemicalchips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import site.budanitskaya.chemistryquiz.fine.QuizListAdapter
import site.budanitskaya.chemistryquiz.fine.QuizListFragmentDirections
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.topics.topics

class GameListFragment : Fragment() {

    val adapter: GamesListAdapter by lazy {
        GamesListAdapter(listOf("Chemical Chips", "Wise Koa", "Guess the scientist")) {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gameslist, container, false)
        val gameRecycler = view.findViewById<RecyclerView>(R.id.game_recycler)
        gameRecycler.adapter = adapter
        gameRecycler.layoutManager = GridLayoutManager(requireContext(), 1)
        return view
    }
}