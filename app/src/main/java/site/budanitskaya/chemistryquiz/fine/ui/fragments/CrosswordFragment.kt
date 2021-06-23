package site.budanitskaya.chemistryquiz.fine.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.constants.LOG_TAG
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentCrosswordBinding
import site.budanitskaya.chemistryquiz.fine.ui.activities.MainActivity
import site.budanitskaya.chemistryquiz.fine.ui.adapters.CrosswordAdapter
import site.budanitskaya.chemistryquiz.fine.ui.adapters.ExplanationsAdapter
import site.budanitskaya.chemistryquiz.fine.ui.viewmodels.MainViewModel
import site.budanitskaya.chemistryquiz.fine.utils.answersList


class CrosswordFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }

    private lateinit var binding: FragmentCrosswordBinding

    val explanationsAdapter = ExplanationsAdapter(
        answersList
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_crossword, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.activity = this
        binding.crosswordRecyclerview.adapter = CrosswordAdapter(viewModel)
        val adapter = binding.crosswordRecyclerview.adapter as CrosswordAdapter
        viewModel.cellList.observe(viewLifecycleOwner, { list ->
            (binding.crosswordRecyclerview.adapter as CrosswordAdapter).submitList(list)
        })
        return binding.root
    }

}