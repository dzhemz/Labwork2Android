package com.example.labwork2android

import androidx.recyclerview.selection.ItemKeyProvider

class RealEstateUnitKeyProvider(private val items: List<RealEstateUnit>): ItemKeyProvider<RealEstateUnit>(
    ItemKeyProvider.SCOPE_CACHED)  {
    override fun getKey(position: Int) = items.getOrNull(position)
    override fun getPosition(key: RealEstateUnit) = items.indexOf(key)
}