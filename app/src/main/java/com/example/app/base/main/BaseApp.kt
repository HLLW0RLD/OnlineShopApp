package com.example.app.base.main

import android.app.Application
import androidx.room.Room
import com.example.app.base.di.EntranceComponents
import com.example.core.db.UserDAO
import com.example.core.db.UserDB
import com.example.home.di.HomeComponents
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class BaseApp: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(
                HomeComponents().apiModule,
                EntranceComponents().coreModule
            )
        }
    }
    companion object{
        var appInstance: BaseApp? = null

        private var db: UserDB? = null
        private const val DB_NAME = "UserData.db"
        fun getUserDAO(): UserDAO {
            if (db == null) {
                synchronized(UserDB::class.java) {
                    if (db == null) {
                        appInstance?.let { app ->
                            db = Room.databaseBuilder(
                                app.applicationContext,
                                UserDB::class.java,
                                DB_NAME
                            ).build()
                        }
                    }
                }
            }
            return db!!.userData()
        }
    }
}