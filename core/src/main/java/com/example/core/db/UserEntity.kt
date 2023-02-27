package com.example.core.db

import androidx.room.Entity

@Entity
data class UserEntity(val firstName: String, val lastName: String, val email: String)