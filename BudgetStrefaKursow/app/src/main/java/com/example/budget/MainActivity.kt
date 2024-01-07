package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import com.example.budget.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.myButton.setOnClickListener {
//            val intent = Intent(applicationContext, SecondActivity::class.java)
            val intent = Intent(Intent.ACTION_DIAL)
            val data = binding.myEditText.text.toString()
            intent.data = "tel:$data".toUri()
//            intent.putExtra("MY_DATA", data)
            startActivity(intent)
        }


    }
}