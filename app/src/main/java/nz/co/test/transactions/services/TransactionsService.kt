package nz.co.test.transactions.services

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val URL = "https://gist.githubusercontent.com"

private val moshi = Moshi.Builder()
    .add(BigDecimalAdapter)
    .add(OffsetDateTimeAdapter)
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(URL)
    .build()

interface TransactionsService {
    @GET("/Josh-Ng/500f2716604dc1e8e2a3c6d31ad01830/raw/4d73acaa7caa1167676445c922835554c5572e82/test-data.json")
    suspend fun retrieveTransactions(): Array<Transaction>
}

object TransactionsApi {
    val retrofitService: TransactionsService by lazy { retrofit.create(TransactionsService::class.java) }
}

