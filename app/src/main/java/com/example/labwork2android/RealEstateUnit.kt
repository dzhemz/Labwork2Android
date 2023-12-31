package com.example.labwork2android

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RealEstateUnit(val title: String,
                          val address: String,
                          val area: Int,
                          var cost: Int,
                          var rent: Int,
                          var ownerName: String,
                          var description: String,
                          var shortDescription: String): Parcelable