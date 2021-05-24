package com.example.login_contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_contact.databinding.CategoryItemLayoutBinding

class CustomAdapter(val listener:OnitemClickListener): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    val categories = mutableListOf<Category>()

    fun setUpCategories(categories: List<Category>){
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }
    fun addCategory(category: Category){
        this.categories.add(category)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CategoryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.bindItem(category)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    inner class ViewHolder(private val binding: CategoryItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{


        fun bindItem(category: Category){
            binding.categoryTextView.text = category.name
        }
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val category = categories[adapterPosition]
            listener.onItemClicked(category)
        }

    }

    interface OnitemClickListener{
        fun onItemClicked(category: Category)
    }

}