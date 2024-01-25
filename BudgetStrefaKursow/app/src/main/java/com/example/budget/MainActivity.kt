package com.example.budget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.net.toUri
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.fragment.app.commit
import androidx.navigation.fragment.NavHostFragment
import com.example.budget.databinding.ActivityMainBinding
import com.example.budget.fragments.FirstFragment
import com.example.budget.fragments.SecondFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val intKey = intPreferencesKey("myInt")
//    private var myValue = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                it[intKey] = 777
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {prefs ->
                val myPrefs = prefs[intKey]
                Log.d("PREFS", "MyPrefs:$myPrefs")
            }
        }

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragContainerFirst.id) as NavHostFragment
        val navController = navHostFragment.navController


//        val firstFragment = FirstFragment()
//        val secondFragment = SecondFragment()
//
//        supportFragmentManager.commit {
//            add(R.id.fragContainerFirst, firstFragment)
//            add(R.id.fragContainerFirst, secondFragment)
//        }

//        if(savedInstanceState != null){
//            val mValue = savedInstanceState.getInt("VALUE")
//            myValue = mValue
//            binding.myTextView.text = myValue.toString()
//        }
//
//        binding.myButton.setOnClickListener {
//            val rand = Random.nextInt(0, 100)
//            myValue = rand
//            binding.myTextView.text = myValue.toString()
//        }

//        binding.myButton.setOnClickListener {
////            val intent = Intent(applicationContext, SecondActivity::class.java)
//            val intent = Intent(Intent.ACTION_DIAL)
//            val data = binding.myEditText.text.toString()
//            intent.data = "tel:$data".toUri()
////            intent.putExtra("MY_DATA", data)
//            startActivity(intent)
//        }
    }
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putInt("VALUE", myValue)
//    }
}