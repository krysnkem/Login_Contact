package com.example.login_contact.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.login_contact.db.dao.UserDao
import com.example.login_contact.db.entities.UserEntity


@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    companion object{
        @Volatile private var INSTANCE: UserDatabase? = null
        private  val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE?: synchronized(LOCK) {
            INSTANCE?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context, UserDatabase::class.java, "users.db").build()
    }
}