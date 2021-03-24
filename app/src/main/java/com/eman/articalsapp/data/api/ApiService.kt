package com.eman.articalsapp.data.api

import com.eman.articalsapp.domain.model.ArticalAll
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("viewed/7.json")
    suspend fun getArtical(@Query ("api-key") apikey :String ): ArticalAll

}