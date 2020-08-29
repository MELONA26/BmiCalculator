package com.conpany4246.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDate()

        resultButton.setOnClickListener{
            saveData(heightEditText.text.toString().toInt(),
                     weightEditText.text.toString().toInt())

            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }

    private fun saveData(height: Int, weight:Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putInt("KEY_HEIGHT", height)
              .putInt("KEY_WEIGHT", weight)
              .apply()
    }

    private fun loadDate(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getInt("KEY_HEIGHT", -1)
        val weight = pref.getInt("KEY_WEIGHT", -1)

        if(height != -1 && weight != -1){
            heightEditText.setText(height.toString())
            weightEditText.setText(weight.toString())
        }
    }
}