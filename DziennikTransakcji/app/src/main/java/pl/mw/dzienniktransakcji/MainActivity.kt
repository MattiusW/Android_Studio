package pl.mw.dzienniktransakcji

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import pl.mw.dzienniktransakcji.data.room.Transaction
import pl.mw.dzienniktransakcji.data.room.TransactionCategory
import pl.mw.dzienniktransakcji.data.room.TransactionType
import pl.mw.dzienniktransakcji.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val mainVm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        mainVm.insertTransaction(
            Transaction(0,
            1L,
            15f,
            "opis",
            TransactionType.INCOME,
                TransactionCategory.FOOD)
        )
    }

    private fun createTransaction() = Transaction(0,1L, 15f, "Opis", TransactionType.INCOME, TransactionCategory.FOOD )
}