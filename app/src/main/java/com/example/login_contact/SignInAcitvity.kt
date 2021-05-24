package com.example.login_contact

import android.os.Bundle
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_contact.db.UsersRepository
import com.example.login_contact.db.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Handler

class SignInAcitvity : AppCompatActivity() {
    lateinit var userEmail: EditText
    lateinit var userPassword: EditText
    lateinit var repository: UsersRepository
    lateinit var userDetails: Set<UserEntity>
    lateinit var signinBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        init()
    }

    private fun init() {
        userEmail = findViewById(R.id.email_text_input)
        userPassword = findViewById(R.id.password_text_input)
        signinBtn = findViewById(R.id.sign_in_btn)

        var user: UserEntity



        signinBtn.setOnClickListener {
            if (userPassword.text.isNullOrBlank() || userEmail.text.isNullOrBlank()) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show()
            } else {
                val usersRepository:UsersRepository = UsersRepository(this)
                val useremail = userEmail.text.toString()
                val userpassword = userPassword.text.toString()

                GlobalScope.launch {
                    withContext(Dispatchers.IO){

                            val user = usersRepository.login(useremail, userpassword)
                            if(user != null){

                                Toast.makeText(applicationContext, "$user", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(applicationContext, "wrong credenctials", Toast.LENGTH_SHORT).show()
                            }



                    }
                }

            }

        }
    }





}
