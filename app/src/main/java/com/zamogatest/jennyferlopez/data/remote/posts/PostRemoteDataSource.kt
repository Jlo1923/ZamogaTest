package com.zamogatest.jennyferlopez.data.remote.posts

import com.zamogatest.jennyferlopez.data.remote.BaseDataSource
import javax.inject.Inject

class PostRemoteDataSource @Inject constructor(
    private val postService: PostService
): BaseDataSource() {

    suspend fun getPosts() = getResult { postService.getAllPosts() }
    suspend fun getPost(id: Int) = getResult { postService.getPost(id) }
}