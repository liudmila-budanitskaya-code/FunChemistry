package site.budanitskaya.chemistryquiz.fine.ui.testresult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import site.budanitskaya.chemistryquiz.fine.R
import site.budanitskaya.chemistryquiz.fine.databinding.FragmentTestResultBinding

class TestResultFragment : Fragment() {

    private lateinit var args: TestResultFragmentArgs
    private lateinit var binding: FragmentTestResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_test_result, container, false
        )
        args =
            TestResultFragmentArgs.fromBundle(requireArguments())
        if (args.spentTimes.toList().isNotEmpty() && args.areCorrect.toList().isNotEmpty()) {
            setBarChart(args.spentTimes.toList(), args.areCorrect.toList())
        }
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
        if (list.isNotEmpty() && areCorrect.isNotEmpty()) {
            for (i in areCorrect.indices) {
                if (areCorrect[i]) {
                    true_entries.add(BarEntry(i.toFloat(), list[i].toFloat()))
                } else {
                    false_entries.add(BarEntry(i.toFloat(), list[i].toFloat()))
                }
            }
        }
        val barDataSet = BarDataSet(true_entries, "True")

        val falSet = BarDataSet(false_entries, "False")
        falSet.color = resources.getColor(R.color.red)
        val data = BarData(barDataSet, falSet)
        val barChart = binding.barChart
        barChart.data = data
        barDataSet.color = resources.getColor(R.color.green)
        barChart.animateY(3000)
    }
}