package epf.projet_android_cristea_gombert.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import epf.projet_android_cristea_gombert.data.dao.ProductDao
import epf.projet_android_cristea_gombert.data.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}