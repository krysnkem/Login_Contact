package com.example.login_contact.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.login_contact.db.entities.CategoryEntiity

@Dao
interface CategoryDao {

    @Query("Select * from cateorgy")
    fun getAllCategory():LiveData<List<CategoryEntiity>>

    @Insert
    fun addCategory(category: CategoryEntiity)

    @Delete
    fun deleteCategory(category: CategoryEntiity)

    @Update
    fun updateCategory(category: CategoryEntiity)


}