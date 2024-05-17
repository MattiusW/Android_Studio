package pl.mw.dzienniktransakcji.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var uid : Int = 0,
    val date: Long,
    val price: Float,
    val desc: String,
    val type: TransactionType,
    val category: TransactionCategory
)
