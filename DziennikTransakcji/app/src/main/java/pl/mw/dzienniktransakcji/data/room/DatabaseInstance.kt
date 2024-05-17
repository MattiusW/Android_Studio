package pl.mw.dzienniktransakcji.data.room

import android.content.Context
import androidx.room.Room

object DatabaseInstance {
    private var instance: TransactionsDataBase? = null
    fun getInstance(context: Context): TransactionsDataBase {
        if (instance == null) {
            synchronized(TransactionsDataBase::class.java) {
                instance = roomBuild(context)
            }
        }

        return instance!!
    }

    private fun roomBuild(context: Context): TransactionsDataBase =
        Room.databaseBuilder(context, TransactionsDataBase::class.java,
            "transactions_database")
            .fallbackToDestructiveMigration()
            .build()
}