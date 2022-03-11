package com.zamogatest.jennyferlopez.ui.postdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.zamogatest.jennyferlopez.data.entities.Posts
import com.zamogatest.jennyferlopez.data.entities.Users
import com.zamogatest.jennyferlopez.data.repository.PostsRepository
import com.zamogatest.jennyferlopez.data.repository.UserRepository
import com.zamogatest.jennyferlopez.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostDetailViewModel @ViewModelInject constructor(
    private val postRepository: PostsRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _idPost = MutableLiveData<Int>()
    private val _idUser = MutableLiveData<Int>()

    private val _post = _idPost.switchMap { id ->
        postRepository.getPost(id)
    }

    private val _user = _idUser.switchMap { id ->
        userRepository.getUser(id)
    }

    val posts: LiveData<Resource<Posts>> = _post
    val user: LiveData<Resource<Users>> = _user

    fun start(idPost: Int) {
        _idPost.value = idPost
    }

    fun findUser(idUser: Int) {
        _idUser.value = idUser
    }

    fun changeIsRead(post: Posts) {
        viewModelScope.launch(Dispatchers.IO) {
            postRepository.changeIsRead(post)
        }.cancel()
    }

}
