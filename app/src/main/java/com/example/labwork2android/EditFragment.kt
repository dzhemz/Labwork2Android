package com.example.labwork2android

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditFragment: Fragment(R.layout.fragment_edit) {
    private val viewModel: RealEstateUnitModel by activityViewModels()

    @SuppressLint("CutPasteId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val unit = viewModel.selectedUnit.value
        if (unit != null) {
            view.findViewById<TextView>(R.id.title_editor).text = unit.title
            view.findViewById<TextView>(R.id.short_description_editor).text = unit.shortDescription
            view.findViewById<TextView>(R.id.address_editor).text = unit.address
            view.findViewById<TextView>(R.id.area_editor).text = unit.area.toString()
            view.findViewById<TextView>(R.id.cost_editor).text = unit.cost.toString()
            view.findViewById<TextView>(R.id.rent_editor).text = unit.rent.toString()
            view.findViewById<TextView>(R.id.owner_name_editor).text = unit.ownerName
            view.findViewById<TextView>(R.id.description_editor).text = unit.description
        }

        view.findViewById<Button>(R.id.save_changes).setOnClickListener {
            val title = view.findViewById<TextView>(R.id.title_editor)
            val shortDescription = view.findViewById<TextView>(R.id.short_description_editor)
            val address = view.findViewById<TextView>(R.id.address_editor)
            val area = view.findViewById<TextView>(R.id.area_editor)
            val cost = view.findViewById<TextView>(R.id.cost_editor)
            val rent = view.findViewById<TextView>(R.id.rent_editor)
            val ownerName = view.findViewById<TextView>(R.id.owner_name_editor)
            val description = view.findViewById<TextView>(R.id.description_editor)


            if (listOf<TextView>(title, shortDescription, address,
                    ownerName).count { it.text.toString() == "Пусто" || it.text.toString() == ""} > 0){

                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder
                    .setMessage("Значения \"Пусто\"не допустимо для значений," +
                            " также должны быть заполнены обязательные поля: заголовок, адрес, короткое описание, владелец")
                    .setTitle("Внимание")

                val dialog: AlertDialog = builder.create()
                dialog.show()
                return@setOnClickListener
            }

            if (listOf<TextView>(cost, area, rent).count
                { it.text.toString().toIntOrNull() == null} > 0){
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder
                    .setMessage("Значения поля Аренда, Цена, Площадь должны состоять только из цифр")
                    .setTitle("Внимание")

                val dialog: AlertDialog = builder.create()
                dialog.show()
                return@setOnClickListener
            }

            if (shortDescription.length() > 100){
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder
                    .setMessage("Не допускается использование больше 100 символов для короткого описания")
                    .setTitle("Внимание")

                val dialog: AlertDialog = builder.create()
                dialog.show()
                return@setOnClickListener
            }

            if (unit != null){
                viewModel.selectUnit(null)
                viewModel.deleteUnit(unit)
            }

            val newUnit = RealEstateUnit(title.text.toString(),
                address.text.toString(),
                area.text.toString().toInt(),
                cost.text.toString().toInt(),
                rent.text.toString().toInt(),
                ownerName.text.toString(),
                description.text.toString(),
                shortDescription.text.toString(), "")

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = GeocoderApi.retrofitService.getPoints(
                        "1bacd9fe-d1a7-4fa2-b27c-7cbffb86fbca", newUnit.address, "json")
                    val regex = Regex("\"pos\":(?<pos>[^}]*)");
                    val result = regex.find(response)!!
                    val (pos) = result.destructured
                    newUnit.position = pos
                }
                catch (_ :Exception) {}
            }

            viewModel.mutableList.add(newUnit)
            return@setOnClickListener
        }

        view.findViewById<Button>(R.id.delete_changes).setOnClickListener{
            if (unit == null){
                val builder: AlertDialog.Builder = AlertDialog.Builder(context)
                builder
                    .setMessage("Удалять нечего")
                    .setTitle("Внимание")

                val dialog: AlertDialog = builder.create()
                dialog.show()
                return@setOnClickListener
            }
            viewModel.selectUnit(null)
            viewModel.deleteUnit(unit)
            return@setOnClickListener
        }

        view.findViewById<Button>(R.id.go_to_list).setOnClickListener {
            parentFragmentManager.commit{
                setReorderingAllowed(true)
                replace<ListOfRealEstateFragment>(R.id.fragment_container_view)
            }
        }
    }
}