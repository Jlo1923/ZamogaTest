package com.zamogatest.jennyferlopez.data.repository

import com.zamogatest.jennyferlopez.data.entities.Posts
import com.zamogatest.jennyferlopez.data.local.PostsDao
import com.zamogatest.jennyferlopez.data.remote.posts.PostRemoteDataSource
import com.zamogatest.jennyferlopez.utils.performGetOperation
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val remoteDataSource: PostRemoteDataSource,
    private val localDataSource: PostsDao
) {

    fun getPost(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getPost(id) },
        networkCall = { remoteDataSource.getPost(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getPosts() = performGetOperation(
        databaseQuery = { localDataSource.getAllPosts() },
        networkCall = { remoteDataSource.getPosts() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun changeIsRead(post: Posts) {
        localDataSource.update(post)
    }

}