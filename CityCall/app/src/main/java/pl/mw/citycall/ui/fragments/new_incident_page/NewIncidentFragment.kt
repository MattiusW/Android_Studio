package pl.mw.citycall.ui.fragments.new_incident_page

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.mw.citycall.R
import pl.mw.citycall.databinding.FragmentNewIncidentBinding

class NewIncidentFragment : Fragment() {

    private var _binding: FragmentNewIncidentBinding? = null
    private val binding get() = _binding!!
    private val vm by viewModels<NewIncidentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewIncidentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}