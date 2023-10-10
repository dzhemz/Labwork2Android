package com.example.labwork2android

import androidx.recyclerview.selection.ItemDetailsLookup

interface ViewHolderWithDetails<ITtem> {
    fun getItemDetail(): ItemDetailsLookup.ItemDetails<ITtem>
}