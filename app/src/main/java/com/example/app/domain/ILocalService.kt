package com.example.app.domain

interface ILocalService {
    fun insert(firstName: String, lastName: String, email: String)

    fun getUserByName(name: String): Boolean

    fun delete(firstName: String, lastName: String, email: String)
}