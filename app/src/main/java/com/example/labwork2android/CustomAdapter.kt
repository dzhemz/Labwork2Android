package com.example.labwork2android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val listOfUnits: MutableList<RealEstateUnit>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), ViewHolderWithDetails<RealEstateUnit> {
        val title: TextView
        val shortDescription: TextView
        val area: TextView
        val address: TextView
        init {
            title = view.findViewById(R.id.titleShortFragment)
            shortDescription = view.findViewById(R.id.descriptionShortFragment)
            area = view.findViewById(R.id.areaShortFragment)
            address = view.findViewById(R.id.addressShortFragment)
        }

        fun setActivatedState(isActivated: Boolean) {
            itemView.isActivated = isActivated
        }

        override fun getItemDetail() = RealEstateUnitDetails(adapterPosition, listOfUnits[adapterPosition])
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_short_information, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = listOfUnits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = listOfUnits[position].title
        holder.shortDescription.text = listOfUnits[position].shortDescription
        holder.area.text = listOfUnits[position].area.toString()
        holder.address.text = listOfUnits[position].address
    }
}