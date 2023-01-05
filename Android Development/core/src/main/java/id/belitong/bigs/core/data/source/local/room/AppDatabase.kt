package id.belitong.bigs.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.belitong.bigs.core.data.source.local.entity.PlantEntity

@Database(entities = [PlantEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}