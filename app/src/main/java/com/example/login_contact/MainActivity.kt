package com.example.login_contact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.login_contact.db.UsersRepository
import com.example.login_contact.db.entities.UserEntity

class MainActivity : AppCompatActivity() {
    lateinit var usersRepository: UsersRepository
    lateinit var email: EditText
    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var signupBtn: Button
    lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        usersRepository = UsersRepository(this)
        email = findViewById(R.id.email_text_input)
        username = findViewById(R.id.username_text_input)
        password = findViewById(R.id.password_text_input)
        signupBtn = findViewById(R.id.sign_up_btn)
        loginBtn = findViewById(R.id.log_in)


        signupBtn.setOnClickListener {
            if(username.text.isNullOrBlank() || password.text.isNullOrBlank() || email.text.isNullOrBlank()){
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show()
            }else{
                val user = UserEntity(username = username.text.toString(), password = password.text.toString(), email = email.text.toString())
                usersRepository.insertUser(user)
                Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, SignInAcitvity::class.java)
                startActivity(intent)
            }
        }
        loginBtn.setOnClickListener {
            val intent = Intent(this, SignInAcitvity::class.java)
            startActivity(intent)
        }

    }
}