package com.example.labwork2android

import androidx.recyclerview.selection.ItemDetailsLookup

class RealEstateUnitDetails (private val adapterPosition: Int,
                             private val selectedKey: RealEstateUnit) : ItemDetailsLookup.ItemDetails<RealEstateUnit>() {

    override fun getPosition() = adapterPosition
    override fun getSelectionKey() = selectedKey

}

