package com.theplay.aos.fragment.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.MyRecipeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyPageMyRecipeViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _getMyRecipeResponse : MutableLiveData<MyRecipeResponse> = MutableLiveData()
    val getMyRecipeResponse get() = _getMyRecipeResponse

    fun getMyRecipe(pageNumber : Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getMyRecipes(pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        getMyRecipeResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        getMyRecipeResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "MyPageMyRecipeViewModel"
    }
}