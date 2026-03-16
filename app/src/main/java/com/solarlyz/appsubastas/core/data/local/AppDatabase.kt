package com.solarlyz.appsubastas.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.solarlyz.appsubastas.core.data.local.dao.MessageDao
import com.solarlyz.appsubastas.core.data.local.entities.MessageEntity

@Database(entities = [MessageEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
}
