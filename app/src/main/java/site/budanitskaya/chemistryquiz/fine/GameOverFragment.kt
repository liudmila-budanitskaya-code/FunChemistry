package site.budanitskaya.chemistryquiz.fine

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentGameOverBinding
import site.budanitskaya.chemistryquiz.fine.questionscreen.QuestionFragmentArgs

class GameOverFragment : Fragment() {

    private lateinit var args: GameOverFragmentArgs

    private lateinit var binding: FragmentGameOverBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentGameOverBinding>(
            inflater, R.layout.fragment_game_over, container, false
        )
        args = GameOverFragmentArgs.fromBundle(requireArguments())
        setBarChart(args.spentTimes.toList())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnBtn.setOnClickListener {
            findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToQuizListFragment())
        }
    }

    private fun setBarChart(list: List<Long>) {
        val entries = ArrayList<BarEntry>()
        Log.d("setBarChart", "setBarChart: ${list.size}")
        for (i in list.indices){
            entries.add(BarEntry(i.toFloat(), list[i].toFloat()))
        }

        val barDataSet = BarDataSet(entries, "")

        val data = BarData(barDataSet)

        val barChart = binding.barChart
        barChart.data = data // set the data and list of lables into chart
        // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.colorAccent)

        barChart.animateY(3000)
    }
}