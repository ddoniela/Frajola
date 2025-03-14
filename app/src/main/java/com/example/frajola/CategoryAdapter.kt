package com.example.frajola

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val categories: List<String>,
    private val onCategoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedCategory: String = "Tudo"

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val btnCategory : TextView = view.findViewById(R.id.tv_category)

        fun bind(category: String) {
            btnCategory.text = category
            btnCategory.setTextColor(
                if (category == selectedCategory) Color.WHITE else Color.GRAY
            )
            btnCategory.setOnClickListener {
                selectedCategory = category
                onCategoryClick(category)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }
}
