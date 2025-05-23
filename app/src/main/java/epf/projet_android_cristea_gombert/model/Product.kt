package epf.projet_android_cristea_gombert.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Products")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String = "",
    val price: Double = 0.1,
    val description: String = "",
    val category: String = "",
    val image: String = ""
)