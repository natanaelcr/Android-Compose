//package com.example.firstcomposeapp.viewmodel
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.firstcomposeapp.API.MovieAPI
//import com.example.firstcomposeapp.API.RESTApiObject
//import com.example.firstcomposeapp.model.NowPlaying
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.async
//import kotlinx.coroutines.launch
//
//class MovieViewModel:ViewModel() {
//    private val nowPlaying = MutableLiveData<NowPlaying>()
//
//
//    internal fun getNowPlaying():LiveData<NowPlaying>{
//        viewModelScope.launch(Dispatchers.IO) {
//            val nowPlayingResult = RESTApiObject().RESTApiService.getMovies(language = "en-US", page = 1)
//            if (nowPlayingResult.isSuccessful){
//                nowPlayingResult.body().let {
//                    if (it!=null){
//                        val value = async { it }
//                        nowPlaying.postValue(value.await())
//                    }
//                }
//            }
//        }
//        return nowPlaying
//    }
//}