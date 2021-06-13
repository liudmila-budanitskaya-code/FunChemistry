package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.awesomedialog.*
import com.google.android.material.chip.Chip
import kotlinx.coroutines.*
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.utils.StringFormatter.Companion.formatFormula
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentChemChipsQuestionBinding


class ChipsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this, ChipsViewModelFactory())
            .get(ChipsViewModel::class.java)
    }

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

        binding.textGame.text =
            "Select the products of this chemical reaction: "

        chipHashMap = hashMapOf(
            binding.chipOne to viewModel.shuffledRawProducts[0],
            binding.chipTwo to viewModel.shuffledRawProducts[1],
            binding.chipThree to viewModel.shuffledRawProducts[2],
            binding.chipFour to viewModel.shuffledRawProducts[3],
            binding.chipFive to viewModel.shuffledRawProducts[4]
        )

        viewModel.superFunction()
        with(binding) {

            chipOne.setOnClickListener { view ->
                onChipClick(view)
            }

            chipTwo.setOnClickListener { view ->
                onChipClick(view)
            }
            chipThree.setOnClickListener { view ->
                onChipClick(view)
            }
            chipFour.setOnClickListener { view ->
                onChipClick(view)
            }
            chipFive.setOnClickListener { view ->
                onChipClick(view)
            }
        }

        return binding.root
    }

    fun onChipClick(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            if (view is Chip) {
                val values: List<String> = chipHashMap.filterKeys { it == view }.values.toList()
                if (viewModel.rawCorrectProducts.contains(values[0]) && viewModel.rawCorrectProducts.size > 1) {
                    view.animate().alpha(
                        0.0F
                    ).duration = 3000
                    view.visibility = View.GONE
                    viewModel.rawReagentsString.append(("${values[0]} + "))
                    with(binding) {
                        txtChemReaction.invalidate()

                        txtChemReaction.animate().rotation(360F).duration = 2700

                        txtChemReaction.setText(
                            formatFormula(viewModel.rawReagentsString.toString()),
                            TextView.BufferType.SPANNABLE
                        )
                    }
                    viewModel.rawCorrectProducts.remove(values[0])
                } else if (viewModel.rawCorrectProducts.contains(values[0]) && viewModel.rawCorrectProducts.size == 1) {
                    Log.d("onChipClick", "onChipClick: i am here!")
                    view.animate().alpha(
                        0.0F
                    ).duration = 3000
                    view.visibility = View.GONE
                    viewModel.rawReagentsString.append(viewModel.rawCorrectProducts[0])
                    binding.txtChemReaction.setText(
                        formatFormula(viewModel.rawReagentsString.toString()),
                        TextView.BufferType.SPANNABLE
                    )
                    delay(4000)
                    with(binding) {
                        txtChemReaction.invalidate()
                    }
                    viewModel.rawCorrectProducts.remove(values[0])
                    viewModel.setNewReaction()
                    setNewReactionView()
                    delay(2000)
                }
            }
        }
    }

    private fun setChipsVisible() {
        with(binding) {
            chipGroup.alpha = 1F
            chipOne.visibility = View.VISIBLE
            chipTwo.visibility = View.VISIBLE
            chipThree.visibility = View.VISIBLE
            chipFour.visibility = View.VISIBLE
            chipFive.visibility = View.VISIBLE
        }
    }

    private fun setNewReactionView() {

        with(binding) {
            invalidateAll()

            txtChemReaction.alpha = 1F
            setChipsVisible()
            invalidateAll()


           chipHashMap = hashMapOf(
                binding.chipOne to viewModel.shuffledRawProducts[0],
                binding.chipTwo to viewModel.shuffledRawProducts[1],
                binding.chipThree to viewModel.shuffledRawProducts[2],
                binding.chipFour to viewModel.shuffledRawProducts[3],
                binding.chipFive to viewModel.shuffledRawProducts[4]

            )

            invalidateAll()

        }
    }
}