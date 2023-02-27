package com.example.core.db

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.ABORT
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDAO {

    @Insert(onConflict = ABORT)
    fun insert(entity: UserEntity)

    @Query("SELECT * FROM UserDataEntity WHERE name LIKE :name ORDER BY timestamp DESC")
    fun getUserByName(name: String): Single<UserEntity>

    @Delete
    fun delete(entity: UserEntity)

//    @Update
//    fun update(entity: UserDataEntity)

}