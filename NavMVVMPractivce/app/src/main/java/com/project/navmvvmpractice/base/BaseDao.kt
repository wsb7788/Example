package com.project.navmvvmpractice.base

import androidx.room.*

@Dao
interface BaseDao<E: Any> {
    @Insert
    suspend fun insert(vararg elements: E)

    @Insert
    suspend fun insert(elements: List<E>)

    @Delete
    suspend fun delete(vararg elements: E)

    @Delete
    suspend fun delete(elements: List<E>)

    @Update
    suspend fun update(vararg elements: E)

    @Update
    suspend fun update(elements: List<E>)

}