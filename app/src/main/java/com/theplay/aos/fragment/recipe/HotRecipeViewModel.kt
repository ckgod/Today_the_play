package com.theplay.aos.fragment.recipe

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.HotRecipeDetailResponse
import com.theplay.aos.api.model.HotRecipeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HotRecipeViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _hotRecipeResponse : MutableLiveData<HotRecipeResponse> = MutableLiveData()
    val hotRecipeResponse get() = _hotRecipeResponse

    fun getHotRecipe(pageNumber : Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getHotRecipe(pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        hotRecipeResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        hotRecipeResponse.postValue(null)
                    })
        )
    }

    private var _hotRecipeDetailResponse : MutableLiveData<HotRecipeDetailResponse> = MutableLiveData()
    val hotRecipeDetailResponse get() = _hotRecipeDetailResponse

    fun getHotRecipeDetail(tagName : String, pageNumber: Int, pageSize: Int) {
        CompositeDisposable().add(
            remoteRepository.getHotRecipeDetail(tagName, pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        hotRecipeDetailResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        hotRecipeDetailResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "HotRecipeViewModel"
    }
}