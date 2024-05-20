package pl.mw.dzienniktransakcji.ui.income_fragment

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import pl.mw.dzienniktransakcji.MainViewModel
import pl.mw.dzienniktransakcji.R
import pl.mw.dzienniktransakcji.databinding.FragmentIncomeBinding

class IncomeFragment : Fragment() {

    private val viewModel by viewModels<IncomeViewModel>()
    private var _binding: FragmentIncomeBinding? = null
    private val mainVm by activityViewModels<MainViewModel>()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIncomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.incomePieChart.apply {
            isDrawHoleEnabled = true
            setUsePercentValues(true)
            setEntryLabelTextSize(18f)
            setEntryLabelColor(Color.WHITE)
            centerText = "Przychody"
            setCenterTextSize(24f)
            description.isEnabled = false
            setTransparentCircleAlpha(50)
        }

        mainVm.getSumOfIncomeByCategory().observe(viewLifecycleOwner){ transactions ->
            val entries = ArrayList<PieEntry>()
            for (trans in transactions) {
                val pieEntry = PieEntry(trans.total, trans.category.name.lowercase())
                entries.add(pieEntry)
            }

            val pieDateSet = PieDataSet(entries, "")
            val  colors = listOf(
                Color.parseColor("#9038ff"),
                Color.parseColor("#45197d"),
                Color.parseColor("#E536AB"),
                Color.parseColor("#5C03BC")
            )

            pieDateSet.colors = colors

            val pieData = PieData(pieDateSet)
            pieData.setDrawValues(true)
            pieData.setValueFormatter(PercentFormatter(binding.incomePieChart))
            pieData.setValueTextSize(12f)
            pieData.setValueTextColor(Color.WHITE)

            binding.incomePieChart.legend.isEnabled = false
            binding.incomePieChart.data = pieData
            binding.incomePieChart.animateY(500, Easing.EaseInOutQuad)
            binding.incomePieChart.invalidate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}