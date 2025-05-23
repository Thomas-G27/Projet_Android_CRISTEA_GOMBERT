package epf.projet_android_cristea_gombert.dao

import androidx.room.Dao
import androidx.room.Query
import epf.projet_android_cristea_gombert.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>
}