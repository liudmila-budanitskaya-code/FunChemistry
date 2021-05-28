package site.budanitskaya.chemistryquiz.fine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentGameOverBinding

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
        setBarChart()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnBtn.setOnClickListener {
            findNavController().navigate(GameOverFragmentDirections.actionGameOverFragmentToQuizListFragment())
        }
    }

    private fun setBarChart() {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(8f, 0f))
        entries.add(BarEntry(2f, 1f))
        entries.add(BarEntry(5f, 2f))
        entries.add(BarEntry(10f, 3f))
        entries.add(BarEntry(15f, 4f))
        entries.add(BarEntry(19f, 5f))

        val barDataSet = BarDataSet(entries, "")

        val data = BarData(barDataSet)

        val barChart = binding.barChart
        barChart.data = data // set the data and list of lables into chart
        // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.colorAccent)

        barChart?.animateY(3000)
    }

/*    fun setBarChartValues() {
        val xvalues = ArrayList<String>()
        xvalues.add("Monday")
        xvalues.add("Tuesday")
        xvalues.add("Wednesday")
        xvalues.add("Thursday")
        xvalues.add("Friday")
        xvalues.add("Saturday")
        xvalues.add("Sunday")

        val barentries = ArrayList<BarEntry>()

        barentries.add(BarEntry(4f, 0f))
        barentries.add(BarEntry(3.5f, 0f))
        barentries.add(BarEntry(8.9f, 0f))
        barentries.add(BarEntry(5.6f, 0f))
        barentries.add(BarEntry(2f, 0f))
        barentries.add(BarEntry(6f, 0f))
        barentries.add(BarEntry(9f, 0f))

        val barDataSet = BarDataSet(barentries, "First")
        barDataSet.color = resources.getColor(R.color.colorAccent)

        val data = BarData(barDataSet)
        view?.findViewById<BarChart>(R.id.barChart)?.data  = data
        view?.findViewById<BarChart>(R.id.barChart)?.setBackgroundColor(resources.getColor(R.color.colorAccent))
        view?.findViewById<BarChart>(R.id.barChart)?.animateXY(3000, 3000)
    }*/
}