package com.project.navmvvmpractice.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.navmvvmpractice.data.entites.Todo
import com.project.navmvvmpractice.data.entites.User


@Database(entities = [User::class, Todo::class], version = 6)

abstract class Database:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun todoDao(): TodoDao
}