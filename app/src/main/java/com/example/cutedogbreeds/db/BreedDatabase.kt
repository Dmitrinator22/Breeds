package com.example.cutedogbreeds.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Breed::class), version = 1, exportSchema = false)
abstract class BreedDatabase :RoomDatabase(){

    abstract fun breedDao(): BreedDao

    companion object{

        @Volatile
        private var INSTANCE: BreedDatabase? = null

        fun getDatabase(
            context: Context,
            scope:CoroutineScope

        ): BreedDatabase{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,BreedDatabase::class.java,"breed_database"
                )
                    .addCallback(BreedDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

    private class BreedDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database->
                scope.launch {
                    populateDatabase(database.breedDao())
                }

            }

        }

        suspend fun populateDatabase(breedDao: BreedDao){
            //var list:List<String> = mutableListOf("D", "A", "E")

            //breedDao.deleteAll()

            //breedDao.insert(Breed(0,"pitbull"))

            /*
            breedDao.insertLink(Link(0, "Hallo", "pitbull"))
            breedDao.insertLink(Link(1, "HEHE", "pitbull"))
             */

        }

    }



}