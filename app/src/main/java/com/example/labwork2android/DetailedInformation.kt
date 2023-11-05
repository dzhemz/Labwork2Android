package com.example.labwork2android

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.labwork2android.databinding.FragmentDetailedInformationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailedInformation : Fragment(R.layout.fragment_detailed_information) {
    private val viewModel: RealEstateUnitModel by activityViewModels()
    private var _binding: FragmentDetailedInformationBinding? = null;
    private val binding get() = _binding!!
    /*
    private val positionObserver = Observer<String> { position ->
        binding.geoData.text = position
    }
    */

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedInformationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val unit = viewModel.selectedUnit.value
        if (unit != null) {
            binding.title.text = unit.title
            binding.shortDescription.text = unit.shortDescription
            binding.address.text = unit.address
            binding.area.text = unit.area.toString()
            binding.cost.text = unit.cost.toString()
            binding.rent.text = unit.rent.toString()
            binding.ownerName.text = unit.ownerName
            binding.description.text = unit.description
            binding.geoData.text = unit.position
            //viewModel.position.observeForever(positionObserver)
        }

        binding.back.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ListOfRealEstateFragment>(R.id.fragment_container_view)
            }
        }

        binding.edit.setOnClickListener {
            parentFragmentManager.commit{
                setReorderingAllowed(true)
                replace<EditFragment>(R.id.fragment_container_view)
            }
        }
    }
}