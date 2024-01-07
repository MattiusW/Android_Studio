package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import com.example.budget.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var myValue = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState != null){
            val mValue = savedInstanceState.getInt("VALUE")
            myValue = mValue
            binding.myTextView.text = myValue.toString()
        }

        binding.myButton.setOnClickListener {
            val rand = Random.nextInt(0, 100)
            myValue = rand
            binding.myTextView.text = myValue.toString()
        }

//        binding.myButton.setOnClickListener {
////            val intent = Intent(applicationContext, SecondActivity::class.java)
//            val intent = Intent(Intent.ACTION_DIAL)
//            val data = binding.myEditText.text.toString()
//            intent.data = "tel:$data".toUri()
////            intent.putExtra("MY_DATA", data)
//            startActivity(intent)
//        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("VALUE", myValue)
    }
}