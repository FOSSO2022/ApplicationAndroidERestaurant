package com.isen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.isen.databinding.CellBasketBinding
import com.squareup.picasso.Picasso

class BasketAdapter(private val items: List<BasketItem>, val deleteClickListener: (BasketItem) -> Unit): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    class BasketViewHolder(binding: CellBasketBinding):RecyclerView.ViewHolder(binding.root){
        val dishname: TextView =binding.dishview
        val price: TextView=binding.price
        val quantity: TextView=binding.quantityview
        val delete: ImageButton=binding.delete
        val image: ImageView=binding.foodimage
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(CellBasketBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val basketItem=items[position]
        holder.dishname.text=basketItem.dish.name
        holder.price.text="${basketItem.dish.prices.first().price}€"
        holder.quantity.text=basketItem.quantity.toString()
        holder.delete.setOnClickListener(){
            deleteClickListener.invoke(basketItem)
        }
        Picasso.get().load(basketItem.dish.getThumbnailURL()).placeholder(R.drawable._04).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

}