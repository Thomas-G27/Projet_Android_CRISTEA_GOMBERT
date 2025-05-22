package epf.projet_android_cristea_gombert.data.dao

import androidx.room.Dao
import androidx.room.Query
import epf.projet_android_cristea_gombert.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<Product>>
}