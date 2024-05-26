package pl.mw.dzienniktransakcji.ui.add_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import pl.mw.dzienniktransakcji.MainActivity
import pl.mw.dzienniktransakcji.MainViewModel
import pl.mw.dzienniktransakcji.R
import pl.mw.dzienniktransakcji.data.room.Transaction
import pl.mw.dzienniktransakcji.data.room.TransactionCategory
import pl.mw.dzienniktransakcji.data.room.TransactionType
import pl.mw.dzienniktransakcji.databinding.FragmentAddTransactionBinding
import pl.mw.dzienniktransakcji.ui.date_picker.TransactionDatePicker
import java.util.Calendar

class AddTransactionFragment : Fragment() {

    private val viewModel by viewModels<AddTransactionViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentAddTransactionBinding? = null
    private  val binding get() = _binding!!
    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            (requireActivity() as MainActivity).setBottomNavVisibility(true)
            isEnabled = false
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTransactionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCurrentDate()
        handleOnBackPressed()
        val adapter = ArrayAdapter(requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            TransactionCategory.values().map { enum -> enum.name })

        binding.categorySpinner.adapter = adapter

        binding.calendarImage.setOnClickListener {
            showDatePickerDialog()
        }

        binding.amountEt.setOnFocusChangeListener { y, hasFocus ->
            if(!hasFocus) {
              binding.amountEt.setBackgroundResource(R.drawable.text_view_outline)
                binding.errorHintTv.visibility = View.INVISIBLE
            }
        }

        binding.amountEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.amountEt.setBackgroundResource(R.drawable.text_view_outline_focus)
                binding.errorHintTv.visibility = View.INVISIBLE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                try{
                    s.toString().toFloat()
                }catch (e: Exception) {
                    binding.amountEt.setBackgroundResource(R.drawable.text_view_outline_wrong)
                    binding.errorHintTv.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.saveBtn.setOnClickListener {
            val trans = createTransaction()
            mainVm.insertTransaction(trans!!)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupCurrentDate() {
        val date = Calendar.getInstance()
        viewModel.date = date.timeInMillis
    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    private fun showDatePickerDialog() {
        val newDatePicker = TransactionDatePicker {day, month, year ->

            val dayPlaceholder = if (day < 10) "0$day" else "$day"
            binding.dayTv.text = dayPlaceholder

            val monthPlaceholder = if (month + 1 < 10) "0${month + 1}" else "${month + 1}"
            binding.monthTv.text = monthPlaceholder

            binding.yearTv.text = year.toString()

            val date = Calendar.getInstance()
            date.set(year, month, day)
            viewModel.date = date.timeInMillis
        }

        newDatePicker.show(parentFragmentManager, "DatePicker")
    }

    private fun createTransaction(): Transaction? {
        val type = when(binding.typeRg.checkedRadioButtonId) {
            binding.incomeRb.id -> TransactionType.INCOME
            else -> TransactionType.OUTCOME
        }

        val category = when(binding.categorySpinner.selectedItem.toString()) {
            "FOOD" -> TransactionCategory.FOOD
            "OTHERS" -> TransactionCategory.OTHERS
            "HOUSEHOLD" -> TransactionCategory.HOUSEHOLD
            "TRANSPORTATION" -> TransactionCategory.TRANSPORTATION
            else -> TransactionCategory.OTHERS
        }

        var amount = binding.amountEt.text.toString()
        val desc = binding.descEt.text.toString()

        //handle empty string exception
        if(TextUtils.isEmpty(amount)){
            amount = "0"
        }

        return Transaction(0, viewModel.date, amount.toFloat(), desc, type, category)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}