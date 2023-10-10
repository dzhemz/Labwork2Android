package com.example.labwork2android

import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView


class MyDetailsLookup(private val units: RecyclerView) : ItemDetailsLookup<RealEstateUnit>() {
    override fun getItemDetails(e: MotionEvent) = units.findChildViewUnder(e.x, e.y)?.let {
            (units.getChildViewHolder(it) as? ViewHolderWithDetails<RealEstateUnit>)?.getItemDetail()
        }
}

