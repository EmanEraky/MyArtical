package com.eman.articalsapp.data.repo

import com.eman.articalsapp.data.api.ApiService
import com.eman.articalsapp.domain.model.ArticalAll
import com.eman.articalsapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiArticalRepo @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getArtical(apikey: String): Flow<Resource<ArticalAll>> {
        return flow {
            emit(Resource.loading(null))
            val resource = try {
                val artical = apiService.getArtical(apikey)
                Resource.success(artical)
            } catch (e: Throwable) {
                Resource.error(e.toString(), null)
            }
            emit(resource)
        }
    }
}