package site.budanitskaya.chemistryquiz.fine.ui.topiclist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuizListBinding
import site.budanitskaya.chemistryquiz.fine.dialogs.showAlertDialog
import site.budanitskaya.chemistryquiz.fine.domain.Topic
import site.budanitskaya.chemistryquiz.fine.lists.topics
import site.budanitskaya.chemistryquiz.fine.ui.chipscreen.ChipsViewModel
import site.budanitskaya.chemistryquiz.fine.ui.chipscreen.ChipsViewModelFactory


class TopicListFragment : Fragment() {

    var position: Int = 0

    private val viewModel by lazy {
        ViewModelProvider(this, TopicListViewModelFactory())
            .get(TopicListViewModel::class.java)
    }

    private lateinit var binding: FragmentQuizListBinding

    val adapter: TopicListAdapter by lazy {
        TopicListAdapter(topics, { ifDialogIsToBeShown(it) }, viewModel.numOfOpenLevels)
    }

    fun ifDialogIsToBeShown(topic: Topic) {
        if (topic.id < viewModel.numOfOpenLevels) {
            showAlertDialog(topic, this)
        }
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


    /*fun setOpenedLevelView() {
        viewModel.numOfOpenLevels.observe()
        // Other code to setup the activity...

        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newNumOfOpenLevels ->
            // Update the UI, in this case, a TextView.
            nameTextView.text = newNumOfOpenLevels
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.currentName.observe(this, nameObserver)
    }*/

}