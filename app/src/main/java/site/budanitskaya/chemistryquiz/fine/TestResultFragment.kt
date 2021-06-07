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
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentTestResultBinding

class TestResultFragment : Fragment() {

    private lateinit var args: TestResultFragmentArgs

    private lateinit var binding: FragmentTestResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentTestResultBinding>(
            inflater, R.layout.fragment_test_result, container, false
        )
        args = TestResultFragmentArgs.fromBundle(requireArguments())
        /*if(activity != null && activity is MainActivity)
            (activity as MainActivity).findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE
*/
        setBarChart(args.spentTimes.toList(), args.areCorrect.toList())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.returnBtn.setOnClickListener {
            findNavController().navigate(TestResultFragmentDirections.actionGameOverFragmentToQuizListFragment())
        }
    }

    private fun setBarChart(list: List<Long>, areCorrect: List<Boolean>) {
        val true_entries = ArrayList<BarEntry>()
        val false_entries = ArrayList<BarEntry>()
        Log.d("setBarChart", "setBarChart: ${list.size}")
        for (i in list.indices){
            if(areCorrect[i]){
                true_entries.add(BarEntry(i.toFloat(), list[i].toFloat()))
            } else {
                false_entries.add(BarEntry(i.toFloat(), list[i].toFloat()))
            }
        }

        val barDataSet = BarDataSet(true_entries, "True")
        barDataSet.color = R.color.colorPrimary
        val falSet = BarDataSet(false_entries, "False")
        falSet.color = R.color.colorAccent

        val data = BarData(barDataSet, falSet)

        val barChart = binding.barChart
        barChart.data = data // set the data and list of lables into chart
        // set the description

        //barDataSet.setColors(ColorTemplate.COLORFUL_COLORS)
        barDataSet.color = resources.getColor(R.color.colorAccent)

        barChart.animateY(3000)
    }
}