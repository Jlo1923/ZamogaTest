package com.zamogatest.jennyferlopez.data.remote.posts

import com.zamogatest.jennyferlopez.data.entities.Posts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    suspend fun getAllPosts() : Response<List<Posts>>


    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Response<Posts>
}