package site.budanitskaya.chemistryquiz.fine.ui.chipscreen

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentChipsOverBinding


class ChipsOverFragment : Fragment() {

    private lateinit var binding: FragmentChipsOverBinding

    private lateinit var args: ChipsOverFragmentArgs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_chips_over, container, false
        )
        args = ChipsOverFragmentArgs.fromBundle(
            requireArguments()
        )
        val sum = args.number.sum()
        binding.numberTxt.text = (sum / 1000).toString()

        viewLifecycleOwner.lifecycleScope.launch {
            var x = 0
            while (x < sum/1000) {
                binding.numberTxt.text = x.toString()
                x++
                delay(100)
            }

        }

        val pientri = mutableListOf<PieEntry>()

        for (i in args.number) {
            pientri.add(PieEntry(i.toFloat()))
        }

        val x = PieDataSet(pientri, "Label")
        x.setColors(Color.RED, Color.GREEN, Color.BLUE);
        with(binding.chart1) {
            setUsePercentValues(true)
            getDescription().setEnabled(false)
            setExtraOffsets(5f, 10f, 5f, 5f)

            dragDecelerationFrictionCoef = 0.95f

            data = PieData(x)


            setHoleColor(Color.WHITE)

            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)

            holeRadius = 58f
            transparentCircleRadius = 61f

            setDrawCenterText(true)

            setRotationAngle(0f)
            // enable rotation of the chart by touch
            // enable rotation of the chart by touch
            setRotationEnabled(true)
            setHighlightPerTapEnabled(true)
        }


        return binding.root

    }


}