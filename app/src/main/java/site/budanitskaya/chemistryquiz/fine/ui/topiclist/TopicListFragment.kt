package site.budanitskaya.chemistryquiz.fine.ui.topiclist


import android.content.Intent
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
import com.firebase.ui.auth.data.model.User
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuizListBinding
import site.budanitskaya.chemistryquiz.fine.dialogs.showAlertDialog
import site.budanitskaya.chemistryquiz.fine.domain.Topic
import site.budanitskaya.chemistryquiz.fine.lists.topics
import site.budanitskaya.chemistryquiz.fine.ui.login.LoginActivity


class TopicListFragment : Fragment(), PreferenceListener {

    var position: Int = 0
    var database = FirebaseDatabase.getInstance().reference

    private val viewModel by lazy {
        ViewModelProvider(this, TopicListViewModelFactory())
            .get(TopicListViewModel::class.java)
    }

    private lateinit var binding: FragmentQuizListBinding

    val adapter: TopicListAdapter by lazy {
        TopicListAdapter(topics, { ifDialogIsToBeShown(it) }, viewModel.numOfOpenLevels)
    }

    fun ifDialogIsToBeShown(topic: Topic) {
        if (topic.id <= viewModel.numOfOpenLevels) {
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


        Log.d("onCreateView", "onCreateView: ${database.child("levels").key}")
        val db = Firebase.database
        val messagesRef = db.reference.child("levels")
        val options = FirebaseRecyclerOptions.Builder<UserInformation>()
            .setQuery(messagesRef, UserInformation::class.java)
            .build()
        Log.d("dddddddddd", "onCreateView: ${options.snapshots}")
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

    override fun onChange() {
        Log.d("zzzzz", "onChange: zzzzzz")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.reset_progress -> {
                PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())
                    .edit().putInt("key_level", 1).apply()
                viewModel.numOfOpenLevels = PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext()).getInt("key_level", 1)
                adapter.notifyDataSetChanged()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}