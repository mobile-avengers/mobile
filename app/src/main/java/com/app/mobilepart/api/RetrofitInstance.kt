package com.app.mobilepart.api

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Field


object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:10101"

    private val retrofit by lazy {
        val gson = GsonBuilder()
            .setLenient()
            .setExclusionStrategies(
                object: ExclusionStrategy {
                    override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                        return false
                    }

                    override fun shouldSkipField(f: FieldAttributes?): Boolean {
                        val fieldName = f?.name
                        val theClass = f?.declaringClass
                        return isFieldInSuperclass(theClass, fieldName)
                    }

                    private fun isFieldInSuperclass(subclass: Class<*>?, fieldName: String?): Boolean {
                        var superclass: Class<*>? = subclass?.superclass
                        var field: Field?

                        while (superclass != null) {
                            field = getField(superclass, fieldName)

                            if (field != null)
                                return true

                            superclass = superclass.superclass
                        }

                        return false
                    }

                    private fun getField(theClass: Class<*>, fieldName: String?): Field? {
                        return try {
                            theClass.getDeclaredField(fieldName)
                        } catch (e: Exception) {
                            null
                        }
                    }
                }
            )
            .create()

        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.interceptors().add(interceptor)

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api: OrderServicedApi by lazy {
        retrofit.create(OrderServicedApi::class.java)
    }
}
