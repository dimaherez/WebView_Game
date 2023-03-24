package com.example.testsmartapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testsmartapps.sqlite.SQLiteHelper
import com.example.testsmartapps.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var sqliteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sqliteHelper = SQLiteHelper.getInstance(this)

        viewModel.response.observe(this) {
            findViewById<TextView>(R.id.tv).text = it.toString()
            if (it != null){
                if (sqliteHelper.getValue() == null) {
                    sqliteHelper.insertValue(it)
                }

                val intent = if (sqliteHelper.getValue()!!) {
                    Intent(this, WebViewActivity::class.java)
                } else {
                    Intent(this, GameActivity::class.java)
                }

                startActivity(intent)
            }
        }
    }
}