package com.eman.articalsapp.data.repo

import com.eman.articalsapp.domain.model.ArticalAll
import com.eman.articalsapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    suspend fun getArtical(apikey :String): Flow<Resource<ArticalAll>>

}