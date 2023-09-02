package com.example.todolixo2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolixo2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
//    val ds: DataStore<Preferences> by preferencesDataStore("s")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val flow = ds.data
    }
}