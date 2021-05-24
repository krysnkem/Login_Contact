package com.example.login_contact.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.login_contact.db.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersRepository(context: Context) {



    val db = UserDatabase.invoke(context)
    val dbDao = db.userDao()

    fun getAllUsers(): LiveData<List<UserEntity>> = dbDao.getAllUsers()


    fun login( useremail:String,  passWord:String): UserEntity = dbDao.login(useremail, passWord)

    fun insertUser(userEntity: UserEntity){
        GlobalScope.launch{
            withContext(Dispatchers.IO){
                dbDao.insertUser(userEntity)
            }

        }
    }
}