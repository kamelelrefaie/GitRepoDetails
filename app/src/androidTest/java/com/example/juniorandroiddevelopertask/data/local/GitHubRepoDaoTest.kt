package com.example.juniorandroiddevelopertask.data.local

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before


import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class GitHubRepoDaoTest {


    private lateinit var database: GitHubDatabase
    private lateinit var dao: GitHubRepoDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GitHubDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.gitHubDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertRepos() = runBlocking {
        val list = mutableListOf<GitHubRepoEntity>()
        for (i in 0..2) {
            list.add(GitHubRepoEntity(0, 0, 0, "j", "h", "h", "h", i))
        }
        dao.insertRepos(list)

        val allItems = dao.searchRepos("", 0, 10)

        assertEquals(allItems, list)

    }

    @Test
    fun insertReposFalse() = runBlocking {
        val list = mutableListOf<GitHubRepoEntity>()
        for (i in 0..2) {
            list.add(GitHubRepoEntity(0, 0, 0, "j", "h", "h", "h", i))
        }
        dao.insertRepos(list)

        val allItems = dao.searchRepos("", 0, 10)
        allItems[0].repo_id = 10
        assertNotEquals(allItems, list)

    }

    @Test
    fun getRepoById() = runBlocking {
        val list = mutableListOf<GitHubRepoEntity>()
        for (i in 0..2) {
            list.add(GitHubRepoEntity(0, 0, 0, "j", "h", "h", "h", i))
        }
        dao.insertRepos(list)

        val item = dao.getRepoById(2)

        assertTrue(item != null)

    }

    @Test
    fun deleteAllRepos() = runBlocking {
        val list = mutableListOf<GitHubRepoEntity>()
        for (i in 0..2) {
            list.add(GitHubRepoEntity(0, 0, 0, "j", "h", "h", "h", i))
        }
        dao.insertRepos(list)
        dao.deleteAllRepos()
        val allItems = dao.searchRepos("", 0, 10)
        assertTrue(allItems.isEmpty())


    }

    @Test
    fun insertGitHubSavedRepoEntity() = runBlocking {
        val item = GitHubSavedRepoEntity(0, 0, 0, "j", "h", "h", "h", true, 1)

        dao.insertGitHubSavedRepoEntity(item)

        val allItems = dao.getSavedRepoById(1)
        assertTrue(allItems != null)
    }

    @Test
    fun deleteSavedRepoEntity() = runBlocking {
        val item = GitHubSavedRepoEntity(0, 0, 0, "j", "h", "h", "h", true, 1)

        dao.insertGitHubSavedRepoEntity(item)

        dao.deleteSavedRepoEntity(1)
        val allItems = dao.getSavedRepos(0, 10)

        assertTrue(allItems.isEmpty())
    }


    @Test
    fun getSavedRepos() = runBlocking {
        val item = GitHubSavedRepoEntity(0, 0, 0, "j", "h", "h", "h", true, 1)
        dao.insertGitHubSavedRepoEntity(item)


        val allItems = dao.getSavedRepos(0, 10)

        assertTrue(allItems.isNotEmpty())
    }


    @Test
    fun thereAreNoGetLovedRepos() = runBlocking {
        val item = GitHubSavedRepoEntity(0, 0, 0, "j", "h", "h", "h", true, 1)

        dao.insertGitHubSavedRepoEntity(item)

        val allItems = dao.getLovedRepos(page = 0, pageSize = 10)
        assertTrue(allItems.isNotEmpty())
    }


    @Test
    fun thereAreGetLovedRepos() = runBlocking {
        val item = GitHubSavedRepoEntity(0, 0, 0, "j", "h", "h", "h", true, 1)

        dao.insertGitHubSavedRepoEntity(item)

        val allItems = dao.getLovedRepos(page = 0, pageSize = 10)
        println(allItems.toString())
        assertTrue(allItems.isNotEmpty())
    }


}