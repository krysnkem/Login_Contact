package com.example.login_contact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_contact.databinding.ActivityCategoryContactsBinding

class CategoryContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryContactsBinding
    private var categoryName: String? = null

    private var adapter = ContactAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryContactsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.categoryContactRecycleView.layoutManager = LinearLayoutManager(this)
        categoryName = intent.getStringExtra(CategoryActivity.CATEGORY_NAME)
        title = categoryName?.replaceFirstChar{
            it.uppercase()
        }


        setUpData(binding)


        for(item in Database.contactlist){
            if(item.categoryName == Category(categoryName!!)){
                adapter.setUpContact(ContactModel(item.name, item.number))
            }
        }


    }



    private fun setUpData(binding: ActivityCategoryContactsBinding) {

        //bind the adapter to the RecycleView
        binding.categoryContactRecycleView.adapter = adapter

        //set up the Alert dialog
        val builder = AlertDialog.Builder(this)

        //get our template
        val view = layoutInflater.inflate(R.layout.alert_dialog_save_contact, null)

        builder.setView(view)

        //get the input fields of the template and the button
        val contactNameInput = view.findViewById<EditText>(R.id.name_textField)
        val contactNumberInput = view.findViewById<EditText>(R.id.number_textField)
        val saveContactBtn = view.findViewById<Button>(R.id.save_ContactBtn)

        contactNumberInput.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                saveContactBtn.isEnabled = p0?.length == 11
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        //create the alert dialog
        val alertDialog = builder.create()

        saveContactBtn.setOnClickListener {
            val contactName = contactNameInput.text.toString()
            val contactPhoneno = contactNumberInput.text.toString()


            Database.contactlist.add(CategoryContactModel(contactName, contactPhoneno, Category(categoryName!!)))

            for(item in Database.contactlist){
                if(item.categoryName == Category(categoryName!!)){
                    Database.contacts.add(ContactModel(item.name, item.number))

                }
            }
            val item = Database.contacts[Database.contacts.size - 1]
            adapter.setUpContact(item)



            alertDialog.dismiss()
            contactNameInput.text.clear()
            contactNumberInput.text.clear()
        }

        binding.categoryContactAddBtn.setOnClickListener {
            alertDialog.show()
        }



    }

}