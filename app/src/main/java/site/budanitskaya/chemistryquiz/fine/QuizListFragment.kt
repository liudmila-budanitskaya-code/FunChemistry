package site.budanitskaya.chemistryquiz.fine

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import site.budanitskaya.chemistryquiz.fine.domain.Topic
import site.budanitskaya.chemistryquiz.fine.topics.topics


class QuizListFragment : Fragment() {

    private lateinit var circleRecycler: RecyclerView

    val adapter: QuizListAdapter by lazy {
        QuizListAdapter(topics) {
            showAlertDialog(it)
        }
    }

    private fun showAlertDialog(topic: Topic) {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("AlertDialog")
        val items = arrayOf("Test Mode", "FlashCard Mode")
        val checkedItem = 1
        alertDialog.setSingleChoiceItems(items, checkedItem) { dialog, which ->

            when (which) {
                0 -> {
                    dialog.dismiss()
                    findNavController().navigate(
                        QuizListFragmentDirections.actionQuizListFragmentToQuestionFragment(
                            topic
                        )
                    )
                }

            1 -> {
                val action =
                QuizListFragmentDirections.actionQuizListFragmentToCardsActivity(topic)

                findNavController().navigate(action)
                dialog.dismiss()
            }
        }
    }

    val alert = alertDialog.create()
    alert.setCanceledOnTouchOutside(true)
    alert.show()
}

override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_quiz_list, container, false)
    circleRecycler = view.findViewById(R.id.game_recycler)

    circleRecycler.adapter = adapter
    val layoutManager = GridLayoutManager(requireContext(), 2)


    circleRecycler.smoothScrollToPosition(0)
    circleRecycler.layoutManager = layoutManager

    viewLifecycleOwner.lifecycleScope.launch {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {

                return when (position % 3) {
                    0 -> 2
                    1, 2 -> 1
                    else -> throw Exception()
                }
            }
        }

        circleRecycler.addItemDecoration(SpacesItemDecoration(230))
        circleRecycler.setHasFixedSize(true)


    }

    adapter.notifyDataSetChanged()


    /*activity?.startActivity(Intent(requireContext(), MainActivity::class.java))*/

    return view
}

override fun onAttach(context: Context) {
    super.onAttach(context)
}


}