package com.example.week1app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadNumberOfAppOpened()
        saveNumberOfAppOpened()
        showSharedText()

    }

    private fun showSharedText() {
        val intent = intent;
        val action = intent.action;
        val type = intent.type;
        val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (Intent.ACTION_SEND == action && type != null) {
            if ("text/plain" == type) {
                if (sharedText != null) {
                    invisible_textView.visibility = View.VISIBLE
                    invisible_textView.text = sharedText
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            return
        }
        chosen_textView.text = ""
        chosen_textView.text = data.getStringExtra("selectedString")
    }

    fun onChooseTextClicked(view: View) {
        val intent = Intent(this, ChooseTextActivity::class.java)
        startActivityForResult(intent, 1)

    }

    fun onShareButtonClicked(view: View) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(Intent.EXTRA_TEXT, chosen_textView.text.toString())
        shareIntent.type = "text/plain"
        if (shareIntent.resolveActivity(packageManager) != null) {
            startActivity(shareIntent);
        }
    }

    private fun saveNumberOfAppOpened() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("numOfOpened", numberOfAppOpened_textView.text.toString().toInt())
        editor.commit()
    }

    private fun loadNumberOfAppOpened() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        var savedValue = sharedPref.getInt("numOfOpened", 0)
        numberOfAppOpened_textView.text = (++savedValue).toString()
    }
}