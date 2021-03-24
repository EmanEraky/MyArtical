package com.eman.articalsapp.presentation.ui.main

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.eman.articalsapp.App
import com.eman.articalsapp.BuildConfig
import com.eman.articalsapp.TestCoroutineRule
import com.eman.articalsapp.data.api.ApiService
import com.eman.articalsapp.data.repo.ApiHelper
import com.eman.articalsapp.domain.model.ArticalAll
import com.eman.articalsapp.domain.usecases.getMainArticalUseCase
import com.eman.articalsapp.utils.NetworkHelper
import com.eman.articalsapp.utils.Resource
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : TestCase() {

    var nyTimesApi: ApiService? = null

    @Mock
    lateinit var networkHelper: NetworkHelper

    //
    @Mock
    lateinit var apiHelper: ApiHelper

    @Mock
    lateinit var mainArticalUseCase: getMainArticalUseCase

    @Mock
    lateinit var _artical: Observer<Resource<ArticalAll>>

    //
    private lateinit var viewModel: MainViewModel

    @get:Rule
    val coroutineRule = TestCoroutineRule()
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")


    @After
    override public fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Before
    override public fun setUp() {
        super.setUp()
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = MainViewModel(mainArticalUseCase, networkHelper)
        nyTimesApi = buildRetrofit().create(ApiService::class.java)
    }

//    @Test
//    fun testGetNetworkHelperTrue() {
//        assertTrue(networkHelper.isNetworkConnected())
//    }
//
//    @Test
//    fun testGetNetworkHelperFalse() {
//        assertFalse(networkHelper.isNetworkConnected())
//    }

//        @Test
//    fun testGetArticalSize() {
//        coroutineRule.runBlockingTest {
//            doReturn(emptyList<ArticalAll>())
//                .`when`(apiHelper)
//                .getArtical(BuildConfig.API_Key)
//            val viewModel = MainViewModel(mainArticalUseCase, networkHelper)
//            viewModel.articals.observeForever(_artical)
//
////            viewModel.getArticalResponse(BuildConfig.API_Key)
////            verify(mainArticalUseCase).getArtical(BuildConfig.API_Key)
//            verify(apiHelper).getArtical(BuildConfig.API_Key)
//            verify(_artical).onChanged(Resource.error("Resource.error",null))
//            viewModel.articals.removeObserver(_artical)
//        }
//
//    }

    @Test
    fun testGetArticalSize(): Unit = runBlocking {
        launch(Dispatchers.Main) {
            val artical = nyTimesApi!!.getArtical(BuildConfig.API_Key)
            assertEquals(artical.results.size, 20)
        }
    }

    @Test
    fun testGetArticalSucessData(): Unit = runBlocking {
        launch(Dispatchers.Main) {

            val artical = nyTimesApi!!.getArtical(BuildConfig.API_Key)
            Assert.assertNotNull(Resource.success(artical).data)
        }
    }


    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
