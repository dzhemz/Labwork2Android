package com.example.labwork2android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.labwork2android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: RealEstateUnitModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<ListOfRealEstateFragment>(R.id.fragment_container_view) }
        setContentView(R.layout.activity_main)
    }
}