package com.example.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String)