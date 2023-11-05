package com.example.labwork2android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RealEstateUnitModel: ViewModel(){
    private val mutableUnit = MutableLiveData<RealEstateUnit?>()
    var mutableList = mutableListOf<RealEstateUnit>()
    val selectedUnit: MutableLiveData<RealEstateUnit?> get() = mutableUnit
    /*
    var position = MutableLiveData<String>();
    */
    fun selectUnit(unit: RealEstateUnit?){
        mutableUnit.value = unit
        /*
        if (unit != null) {
            updatePosition(unit)
        }

         */
    }

    fun deleteUnit(unit: RealEstateUnit){
        mutableList.remove(unit)
    }
    /*
    private fun updatePosition(unit: RealEstateUnit){
        viewModelScope.launch {
            try {

                unit.address.let {
                    val response = GeocoderApi.retrofitService.getPoints(
                        "1bacd9fe-d1a7-4fa2-b27c-7cbffb86fbca", it, "json")

                    val regex = Regex("\"pos\":(?<pos>[^}]*)");
                    val result = regex.find(response)!!
                    val (pos) = result.destructured
                    position.value = pos

                }
            }
            catch (_ :Exception) {}
        }
    }
    */

}

