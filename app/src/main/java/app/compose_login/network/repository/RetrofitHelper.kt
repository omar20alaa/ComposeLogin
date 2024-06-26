package app.compose_login.network.repository

import app.compose_login.BuildConfig
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {

    private val retrofit: Retrofit


    init {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .writeTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES).build()

        retrofit = builder.client(okHttpClient).build()

    }

    fun getAuthService(): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

}