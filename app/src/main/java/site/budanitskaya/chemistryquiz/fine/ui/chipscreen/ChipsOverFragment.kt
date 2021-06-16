package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentChipsOverBinding
import site.budanitskaya.chemistryquiz.fine.ui.test.TestFragment


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

        viewLifecycleOwner.lifecycleScope.launch{
            var x = 86
            while(x > 0){
                binding.txt.text = x.toString()
                x--
                delay(50)
            }

        }


        binding.returnBtn.setOnClickListener {
            findNavController().navigate(R.id.action_chipsOverFragment_to_navigation_game)
        }


        return binding.root

    }




}