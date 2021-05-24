package com.example.login_contact

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_contact.db.UsersRepository
import com.example.login_contact.db.entities.UserEntity
import kotlinx.coroutines.*
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
                val usersRepository: UsersRepository = UsersRepository(this)
                val useremail = userEmail.text.toString()
                val userpassword = userPassword.text.toString()

                CoroutineScope(Dispatchers.IO).launch {


                    val user = usersRepository.login(useremail, userpassword)

                    withContext(Dispatchers.Main){
                        if (user != null) {

                            Toast.makeText(this@SignInAcitvity, "Welcome ${user.username}!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignInAcitvity, CategoryActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@SignInAcitvity, "wrong credenctials", Toast.LENGTH_SHORT)
                                .show()
                        }

                    }



                }

            }

        }
    }


}
