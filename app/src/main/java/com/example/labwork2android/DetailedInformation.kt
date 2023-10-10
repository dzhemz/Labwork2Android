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

class DetailedInformation : Fragment(R.layout.fragment_detailed_information) {
    private val viewModel: RealEstateUnitModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val unit = viewModel.selectedUnit.value
        if (unit != null) {
            view.findViewById<TextView>(R.id.title).text = unit.title
            view.findViewById<TextView>(R.id.short_description).text = unit.shortDescription
            view.findViewById<TextView>(R.id.address).text = unit.address
            view.findViewById<TextView>(R.id.area).text = unit.area.toString()
            view.findViewById<TextView>(R.id.cost).text = unit.cost.toString()
            view.findViewById<TextView>(R.id.rent).text = unit.rent.toString()
            view.findViewById<TextView>(R.id.owner_name).text = unit.ownerName
            view.findViewById<TextView>(R.id.description).text = unit.description
        }
        view.findViewById<Button>(R.id.back).setOnClickListener {
            parentFragmentManager.commit{
                setReorderingAllowed(true)
                replace<ListOfRealEstateFragment>(R.id.fragment_container_view)
            }
        }

        view.findViewById<Button>(R.id.edit).setOnClickListener {
            parentFragmentManager.commit{
                setReorderingAllowed(true)
                replace<EditFragment>(R.id.fragment_container_view)
            }
        }

    }
}