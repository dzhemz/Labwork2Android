package com.example.labwork2android

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.selection.OnItemActivatedListener
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListOfRealEstateFragment : Fragment(R.layout.list_of_real_estate_fragment) {
    private val viewModel: RealEstateUnitModel by activityViewModels()
    private var listOfRealEstate = mutableListOf<RealEstateUnit>();

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val units = view.findViewById<RecyclerView>(R.id.view_of_units)
        units.adapter = CustomAdapter(viewModel.mutableList)
        units.layoutManager = LinearLayoutManager(this.context)

        var tracker = SelectionTracker.Builder<RealEstateUnit>("tracker",
            units,
            RealEstateUnitKeyProvider(listOfRealEstate),
            MyDetailsLookup(units),
            StorageStrategy.createParcelableStorage(RealEstateUnit::class.java))
            .withOnItemActivatedListener(OnItemActivatedListener { item, _ ->
                item.selectionKey?.let {
                    viewModel.selectUnit(it)
                    parentFragmentManager.commit {
                        replace<DetailedInformation>(R.id.fragment_container_view)
                        setReorderingAllowed(true)
                    }
                }
                return@OnItemActivatedListener true
            })
            .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
            .build()

        view.findViewById<Button>(R.id.add_new_unit).setOnClickListener {
            viewModel.selectUnit(null)
            parentFragmentManager.commit{
                replace<EditFragment>(R.id.fragment_container_view)
                setReorderingAllowed(true)
            }
        }
    }


}
