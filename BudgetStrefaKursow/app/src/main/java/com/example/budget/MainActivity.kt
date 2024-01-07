package com.example.budget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.budget.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.myButton.setOnClickListener {
            Toast.makeText(
                applicationContext, "You'r click a button!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.myButton.setOnLongClickListener {
            Toast.makeText(applicationContext,
                binding.myEditText.text.toString(),
                Toast.LENGTH_SHORT).show()

            true
        }
    }
}