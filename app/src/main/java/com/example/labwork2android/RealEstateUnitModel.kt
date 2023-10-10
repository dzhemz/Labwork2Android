package com.example.labwork2android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RealEstateUnitModel: ViewModel(){
    private val mutableUnit = MutableLiveData<RealEstateUnit?>()
    var mutableList = mutableListOf<RealEstateUnit>()

    val selectedUnit: MutableLiveData<RealEstateUnit?> get() = mutableUnit

    fun selectUnit(unit: RealEstateUnit?){
        mutableUnit.value = unit
    }

    fun deleteUnit(unit: RealEstateUnit){
        mutableList.remove(unit)
    }

}

