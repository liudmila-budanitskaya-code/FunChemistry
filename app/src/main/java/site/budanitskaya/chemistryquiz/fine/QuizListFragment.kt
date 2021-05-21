package site.budanitskaya.chemistryquiz.fine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class QuizListFragment : Fragment() {

    private lateinit var circleRecycler: RecyclerView

    val adapter: QuizListAdapter by lazy {
        QuizListAdapter(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16)) {
            findNavController().navigate(QuizListFragmentDirections.actionQuizListFragmentToQuestionFragment("$it".toInt()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_list, container, false)
        circleRecycler = view.findViewById(R.id.circle_recycler)

        circleRecycler.adapter = adapter
        val layoutManager = GridLayoutManager(requireContext(), 2)


        circleRecycler.smoothScrollToPosition(0)
        circleRecycler.layoutManager = layoutManager
       /* circleRecycler.addItemDecoration(SpacesItemDecoration(230))*/
        adapter.notifyDataSetChanged()
        return view
    }
}