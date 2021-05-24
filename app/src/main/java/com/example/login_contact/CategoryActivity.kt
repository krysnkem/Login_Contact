package com.example.login_contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.login_contact.databinding.ActivityCategoryBinding


class CategoryActivity : AppCompatActivity(), CustomAdapter.OnitemClickListener {
    private lateinit var binding : ActivityCategoryBinding
    private val adapter = CustomAdapter(this)
//    var defaultList = mutableListOf<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)

        setContentView(binding.root)
        title = "Contacts Category"
        binding.categoryRecycleView.layoutManager = GridLayoutManager(this, 2)
        setupData(binding)
    }

    private fun setupData(binding: ActivityCategoryBinding) {
        binding.categoryRecycleView.adapter = adapter

        Database.defaultCategoryList = mutableListOf(
            Category("Family"),
            Category("Work"),
            Category("School"),
            Category("Friends")
        )
        adapter.setUpCategories(Database.defaultCategoryList)


        adapter.addCategory(Category("Fans"))

        val builder = AlertDialog.Builder(this)

        val view = layoutInflater.inflate(R.layout.add_category_dialog, null)

        builder.setView(view)

        val newCategoryField = view.findViewById<EditText>(R.id.categoryName_textField)

        val savebtn = view.findViewById<Button>(R.id.save_category_btn)

        val addCategorybtn = binding.addCategoryBtn

        newCategoryField.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                savebtn.isEnabled = p0?.length!! > 2
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        val alertDialog =  builder.create()

        savebtn.setOnClickListener{
            val newCategoryName = newCategoryField.text.toString()
            adapter.addCategory(Category(newCategoryName))
            alertDialog.dismiss()
            newCategoryField.text.clear()

        }


        addCategorybtn.setOnClickListener {
            alertDialog.show()
        }


    }

    override fun onItemClicked(category: Category) {
        val intent = Intent(this, CategoryContactsActivity::class.java)
        intent.putExtra(CATEGORY_NAME, category.name)
        startActivity(intent)
    }





    companion object {
        val CATEGORY_NAME = "categor name"
    }

}