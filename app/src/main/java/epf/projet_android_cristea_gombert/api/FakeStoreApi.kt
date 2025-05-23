package epf.projet_android_cristea_gombert.api


import epf.projet_android_cristea_gombert.model.Product
import retrofit2.http.GET

interface FakeStoreApi {
    @GET("products")
    suspend fun getProducts(): List<Product>
}
