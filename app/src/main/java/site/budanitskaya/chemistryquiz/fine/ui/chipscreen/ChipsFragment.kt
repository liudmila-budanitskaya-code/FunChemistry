package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.android.material.chip.Chip
import kotlinx.coroutines.*
import site.budanitskaya.chemistryquiz.fine.MainApplication
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentChemChipsQuestionBinding
import site.budanitskaya.chemistryquiz.fine.ui.notifications.NotificationUtil
import site.budanitskaya.chemistryquiz.fine.ui.notifications.NotificationsFragment
import site.budanitskaya.chemistryquiz.fine.ui.test.TestFragmentArgs
import site.budanitskaya.chemistryquiz.fine.utils.StringFormatter.Companion.formatFormula


class ChipsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, ChipsViewModelFactory())
            .get(ChipsViewModel::class.java)
    }

    lateinit var args: ChipsFragmentArgs

    var numReactions: Int = 0

    private lateinit var binding: FragmentChemChipsQuestionBinding
    lateinit var chipHashMap: Map<Chip, String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_chem_chips_question, container, false
        )
        binding.viewmodel = viewModel
        binding.fragment = this

        binding.textGame.text =
            "Select the products of this chemical reaction: "

        setChipHashMap()
        args = ChipsFragmentArgs.fromBundle(
            requireArguments()
        )
        numReactions = args.numOfReactions

        viewModel.superFunction()
        viewModel.numOfGuessedReactions.observe(viewLifecycleOwner, Observer { newValue ->
            if (newValue == numReactions) {
                findNavController().navigate(
                    ChipsFragmentDirections.actionChemChipsQuestionFragmentToChipsOverFragment(
                        1
                    )
                )
            }
        })
        return binding.root
    }

    fun onChipClick(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            if (view is Chip) {
                val values: List<String> = chipHashMap.filterKeys { it == view }.values.toList()
                if (viewModel.reaction.correctProducts.contains(values[0]) && viewModel.numOfGuessedProducts.value == 0) {
                    if (PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())
                            .getBoolean(NotificationsFragment.SOUND_PREFERENCE_KEY, false)
                    ) {
                        callService(R.raw.tada_sound)
                    }
                    view.visibility = View.GONE
                    viewModel.rawReagentsString.append(("${values[0]} + "))
                    with(binding) {

                        txtChemReaction.animate().rotation(360F).duration = 2700

                        txtChemReaction.setText(
                            formatFormula(viewModel.rawReagentsString.toString()),
                            TextView.BufferType.SPANNABLE
                        )
                    }
                    viewModel.guessProduct()
                    delay(1000)

                    Log.d(
                        "onChipClick",
                        "onChipClick: ${viewModel.reaction.correctProducts.contains(values[0])}"
                    )
                    Log.d("onChipClick", "onChipClick: ${viewModel.numOfGuessedProducts.value}")
                    Log.d(
                        "onChipClick",
                        "onChipClick: ${viewModel.reaction.correctProducts.size - 1}"
                    )
                } else if (viewModel.reaction.correctProducts.contains(values[0]) && viewModel.numOfGuessedProducts.value == viewModel.reaction.correctProducts.size - 1) {

                    if (PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())
                            .getBoolean(NotificationsFragment.SOUND_PREFERENCE_KEY, false)
                    ) {
                        callService(R.raw.tada_sound)
                    }
                    Log.d("onChipClick", "onChipClick: i am here!")
/*                    view.animate().alpha(
                        0.0F
                    ).duration = 3000*/
                    view.visibility = View.GONE
                    viewModel.rawReagentsString.append(values[0])
                    binding.txtChemReaction.setText(
                        formatFormula(viewModel.rawReagentsString.toString()),
                        TextView.BufferType.SPANNABLE
                    )
                    delay(4000)
                    viewModel.guessProduct()
                    viewModel.setNewReaction()
                    setNewReactionView()
                    viewModel.unGuessProduct()
                    viewModel.guessReaction()
                } else {
                    if (PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())
                            .getBoolean(NotificationsFragment.SOUND_PREFERENCE_KEY, false)
                    ) {
                        callService(R.raw.duck_quack)
                    }
                }
            }
        }
    }

    private fun callService(rawResource: Int) {
        val serviceIntent = Intent(requireContext(), SoundService::class.java)
        serviceIntent.putExtra("soundCode", rawResource)
        requireContext().startService(serviceIntent)
    }

    private fun setNewReactionView() {
        with(binding) {
            invalidateAll()
            setChipHashMap()
        }
    }

    fun setChipHashMap() {
        chipHashMap = hashMapOf(
            binding.chipOne to viewModel.shuffledRawProducts[0],
            binding.chipTwo to viewModel.shuffledRawProducts[1],
            binding.chipThree to viewModel.shuffledRawProducts[2],
            binding.chipFour to viewModel.shuffledRawProducts[3],
            binding.chipFive to viewModel.shuffledRawProducts[4]
        )
    }
}