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
import site.budanitskaya.chemistryquiz.fine.questionscreen.QuestionFragment
import site.budanitskaya.chemistryquiz.fine.topics.topics
import java.lang.Exception

class QuizListFragment : Fragment() {

    private lateinit var circleRecycler: RecyclerView

    val adapter: QuizListAdapter by lazy {
        QuizListAdapter(topics) {
            /*findNavController().navigate(QuizListFragmentDirections.actionQuizListFragmentToQuestionFragment())*/
            /*Toast.makeText(requireContext(), "$it", Toast.LENGTH_LONG).show()*/
            findNavController().navigate(QuizListFragmentDirections.actionQuizListFragmentToQuestionFragment(it))
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
/*        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {

                return when (position % 3) {
                    0 -> 0
                    1 -> 2
                    2 -> 1
                    else -> throw Exception()
                }
            }
        }*/
        /*circleRecycler.addItemDecoration(SpacesItemDecoration(230))*/
        adapter.notifyDataSetChanged()
        return view
    }
}