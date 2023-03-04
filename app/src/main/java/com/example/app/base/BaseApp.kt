package com.example.app.base

import android.app.Application
import androidx.room.Room
import com.example.app.di.AppComponents
import com.example.core.db.UserDAO
import com.example.core.db.UserDB
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
                AppComponents().appModule,  // Application module
                AppComponents().apiModule,  // Remote data module
                AppComponents().coreModule  // DB module
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