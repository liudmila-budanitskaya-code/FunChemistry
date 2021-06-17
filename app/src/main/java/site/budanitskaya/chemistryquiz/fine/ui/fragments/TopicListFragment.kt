package site.budanitskaya.chemistryquiz.fine.ui.fragments


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.*
import dagger.hilt.android.AndroidEntryPoint
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuizListBinding
import site.budanitskaya.chemistryquiz.fine.dialogs.showAlertDialog
import site.budanitskaya.chemistryquiz.fine.models.Topic
import site.budanitskaya.chemistryquiz.fine.lists.topics
import site.budanitskaya.chemistryquiz.fine.ui.adapters.topiclist.IntermittentSpan
import site.budanitskaya.chemistryquiz.fine.ui.adapters.topiclist.SpacesItemDecoration
import site.budanitskaya.chemistryquiz.fine.ui.adapters.topiclist.TopicListAdapter
import site.budanitskaya.chemistryquiz.fine.ui.viewmodelfactories.TopicListViewModelFactory
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.TopicListViewModel
import javax.inject.Inject

@AndroidEntryPoint
class TopicListFragment : Fragment() {

    var position: Int = 0
    var database = FirebaseDatabase.getInstance().reference

    private val viewModel by lazy {
        ViewModelProvider(this, TopicListViewModelFactory(preference))
            .get(TopicListViewModel::class.java)
    }

    @Inject
    lateinit var preference: SharedPreferences

    private lateinit var binding: FragmentQuizListBinding

    val adapter: TopicListAdapter by lazy {
        var numOfOpenLevels =
            preference
                .getInt("key_level", 0)
        Log.d("supermessage", ": $numOfOpenLevels")
        var index = 1
        while (numOfOpenLevels > topics[index].id) {
            topics[index].isOpen = true
            index++
        }
        TopicListAdapter(topics) { ifDialogIsToBeShown(it) }
    }

    fun ifDialogIsToBeShown(topic: Topic) {
        if (topic.isOpen) {
            showAlertDialog(topic, this)
        } else {
            showToast("Complete previous levels!")
        }
    }

    private fun showToast(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz_list, container, false
        )
        showRecyclerView()

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun showRecyclerView() {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = IntermittentSpan()
        with(binding) {
            gameRecycler.adapter = adapter
            gameRecycler.layoutManager = layoutManager
            gameRecycler.addItemDecoration(SpacesItemDecoration(230))
        }
    }

    fun navigateToTest(topic: Topic) {
        findNavController().navigate(
            TopicListFragmentDirections.actionQuizListFragmentToQuestionFragment(
                topic
            )
        )
    }

    fun navigateToCards(topic: Topic) {
        val action =
            TopicListFragmentDirections.actionQuizListFragmentToCardsActivity(
                topic
            )

        findNavController().navigate(action)

    }

    companion object {
        const val MESSAGES_CHILD = "messages"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reset_progress -> {
                var numOfOpenLevels =
                    preference
                        .getInt("key_level", 0)
                var index = 1
                while (numOfOpenLevels > topics[index].id) {
                    topics[index].isOpen = true
                    index++
                }
                if (numOfOpenLevels == 1) {

                } else {
                    preference
                        .edit().putInt("key_level", 1).apply()
                    adapter.notifyDataSetChanged()
                }

                var idd = 0
                for (i in topics) {
                    i.isOpen = i.id == 1
                    idd = i.id
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}