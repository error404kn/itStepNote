package com.example.itstep

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class AppDataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private const val DATABASE_NAME = "database_name"

        private lateinit var instance: AppDataBase

        fun getInstance(context: Context): AppDataBase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    DATABASE_NAME
                ).build()
            }

            return instance
        }
    }
}