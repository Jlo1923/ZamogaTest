package com.zamogatest.jennyferlopez.data.repository

import com.zamogatest.jennyferlopez.data.local.UsersDao
import com.zamogatest.jennyferlopez.data.remote.users.UserRemoteDataSource
import com.zamogatest.jennyferlopez.utils.performGetOperation
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UsersDao
    )  {

    fun getUsers() = performGetOperation(
        databaseQuery = { localDataSource.getAllUsers() },
        networkCall = { remoteDataSource.getUsers() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun getUser(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getUser(id) },
        networkCall = { remoteDataSource.getUser(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

}