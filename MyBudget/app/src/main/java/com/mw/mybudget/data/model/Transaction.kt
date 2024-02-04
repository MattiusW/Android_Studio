package com.mw.mybudget.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.invoke.TypeDescriptor

@Entity(tableName = "transactions_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0,
    val date: Long,
    val price: Float,
    val desc: String,
    val type: TransactionType,
    val category: TransactionCategory,
)
