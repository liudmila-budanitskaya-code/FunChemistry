package site.budanitskaya.chemistryquiz.fine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentGameOverBinding
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentQuestionBinding

class GameOverFragment : Fragment() {

    private lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentGameOverBinding>(
            inflater, R.layout.fragment_game_over, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnBtn.setOnClickListener{
            findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToQuizListFragment())
        }
    }
}