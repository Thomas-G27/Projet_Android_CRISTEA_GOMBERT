package epf.projet_android_cristea_gombert.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Cart (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val products: List<CartItem>
)