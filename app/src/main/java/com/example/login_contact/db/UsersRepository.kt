package com.example.login_contact.db

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.login_contact.db.entities.CategoryEntiity
import com.example.login_contact.db.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersRepository(context: Context) {



    val db = UserDatabase.invoke(context)

    val categoryDao = db.categoryDao()
    val dbDao = db.userDao()

    fun getAllUsers(): LiveData<List<UserEntity>> = dbDao.getAllUsers()

    fun getAllCategories(): LiveData<List<CategoryEntiity>> = categoryDao.getAllCategory()


    fun login( useremail:String,  passWord:String): UserEntity? = dbDao.login(useremail, passWord)

    fun insertUser(userEntity: UserEntity){
        GlobalScope.launch{
            withContext(Dispatchers.IO){
                dbDao.insertUser(userEntity)
            }

        }
    }

    fun addCategory(categoryEntiity: CategoryEntiity){
        GlobalScope.launch {
            withContext(Dispatchers.IO){
                categoryDao.addCategory(categoryEntiity)
            }
        }
    }
}