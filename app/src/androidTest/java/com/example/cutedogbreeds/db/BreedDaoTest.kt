package com.example.cutedogbreeds.db

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.cutedogbreeds.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class BreedDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private lateinit var database: BreedDatabase
    private lateinit var dao: BreedDao



    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            BreedDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.breedDao()
    }

    @After
    fun shut(){
        database.close()
    }

    @Test
    fun insertBreed() = runBlockingTest {

        val dog = Breed(1, "breed", "at/home")
        dao.insert(dog)

        val breeds = dao.getAll().asLiveData().getOrAwaitValue()

        assertThat(breeds).contains(dog)


    }

}