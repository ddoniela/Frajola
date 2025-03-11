package com.example.frajola

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class ProductListAdapter(private var products: List<Product>) :
    ListAdapter<Product, ProductListAdapter.ProductViewHolder>(ProductDiffUtils()) {
    private lateinit var onClickListener: (Product) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bind(contact, onClickListener)
    }

    fun setOnClickListener(onClick: (Product) -> Unit) {
        onClickListener = onClick
    }

    fun updateList(newProducts: List<Product>) {
        this.products = newProducts
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val tvProduct = view.findViewById<TextView>(R.id.tv_product)
        private val tvDescription = view.findViewById<TextView>(R.id.tv_description)
        private val tvPrice = view.findViewById<TextView>(R.id.tv_price)
        private val img = view.findViewById<ImageView>(R.id.image)

        fun bind(product: Product, onClick: (Product) -> Unit) {
            tvProduct.text = product.product
            tvDescription.text = product.description
            tvPrice.text = "R$ " + String.format(Locale.ROOT,"%.2f", product.price)
            img.setImageResource(product.icon)
            view.setOnClickListener {
                onClick.invoke(product)
            }
        }

    }

    class ProductDiffUtils : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.product == newItem.product
        }

    }
}