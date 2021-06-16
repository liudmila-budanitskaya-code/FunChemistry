package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentChipsOverBinding


class ChipsOverFragment : Fragment() {

    private lateinit var binding: FragmentChipsOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_chips_over, container, false
        )

        binding.returnBtn.setOnClickListener {
            findNavController().navigate(R.id.action_chipsOverFragment_to_navigation_game)
        }


        return binding.root

    }


}