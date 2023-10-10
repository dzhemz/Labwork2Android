package com.example.labwork2android

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.OnItemActivatedListener

class UnitClickListener: OnItemActivatedListener<RealEstateUnit> {
    override fun onItemActivated(item: ItemDetailsLookup.ItemDetails<RealEstateUnit>, e: MotionEvent): Boolean {
        return true
    }
}