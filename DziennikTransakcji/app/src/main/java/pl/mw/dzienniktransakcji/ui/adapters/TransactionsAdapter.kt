package pl.mw.dzienniktransakcji.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mw.dzienniktransakcji.R
import pl.mw.dzienniktransakcji.data.room.Transaction
import pl.mw.dzienniktransakcji.data.room.TransactionType
import pl.mw.dzienniktransakcji.databinding.TransactionRowBinding
import java.text.SimpleDateFormat
import java.util.Date

class TransactionsAdapter(private val transations: List<Transaction>,
                          private val onClick: (Transaction, Int) -> Unit
): RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>() {
    inner class TransactionViewHolder(binding: TransactionRowBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener {
                onClick(transations[adapterPosition], adapterPosition)
            }
        }

        val date = binding.dateTv
        val price = binding.priceTv
        val category = binding.categoryTv
        val type = binding.typeTv
        val icon = binding.imageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return TransactionViewHolder(
            TransactionRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return transations.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        bindDate(holder, position)
    }

    private fun bindDate(holder: TransactionViewHolder, position: Int) {

        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val date = Date(transations[position].date)
        val datePlaceholder = sdf.format(date)

        val typeIconResource = when(transations[position].type) {
            TransactionType.INCOME -> R.drawable.wallet_add
            TransactionType.OUTCOME -> R.drawable.wallet__remove
        }

        holder.price.text = transations[position].price.toString()
        holder.category.text = transations[position].category.name
        holder.type.text = transations[position].type.name
        holder.date.text = datePlaceholder
        when(transations[position].type) {
            TransactionType.INCOME -> holder.price.text = "+${transations[position].price}zł"
            TransactionType.OUTCOME -> holder.price.text = "-${transations[position].price}zł"
        }
        holder.icon.setImageResource(typeIconResource)
    }
}