package pl.mw.dzienniktransakcji.ui.transactions_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pl.mw.dzienniktransakcji.MainActivity
import pl.mw.dzienniktransakcji.MainViewModel
import pl.mw.dzienniktransakcji.R
import pl.mw.dzienniktransakcji.databinding.FragmentTransactionsBinding
import pl.mw.dzienniktransakcji.ui.adapters.TransactionsAdapter

class TransactionsFragment : Fragment() {

    private val viewModel by viewModels<TransactionsViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recylerView.layoutManager = LinearLayoutManager(requireContext())
        mainVm.getAllTransactions().observe(viewLifecycleOwner) { transactions ->
            binding.recylerView.adapter = TransactionsAdapter(
                transactions
            ) { transaction, position ->
                mainVm.selectTransaction(transaction)
                (requireActivity() as MainActivity).setBottomNavVisibility(false)
                findNavController().navigate(R.id.editTransactionFragment)
            }

        }
    }
}