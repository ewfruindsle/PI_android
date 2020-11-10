package com.example.week1app

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class ChooseTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_text)
        showTextToBeChosen()

    }

    private fun showTextToBeChosen() {
        val stringsListView = findViewById<ListView>(R.id.strings_listView)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.textToChoose,
            android.R.layout.simple_list_item_1
        )
        stringsListView.adapter = adapter
        stringsListView.setOnItemClickListener { parent, view, position, id ->
            val selectedItemString = parent.getItemAtPosition(position)
            val intent = Intent()
            intent.putExtra("selectedString", selectedItemString.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}