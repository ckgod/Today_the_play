package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theplay.aos.api.RemoteRepository
import com.theplay.aos.api.model.MyRecipeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UserPageMyRecipeViewModel() : ViewModel() {
    private val remoteRepository: RemoteRepository =
        RemoteRepository()

    private var _getUserRecipeResponse : MutableLiveData<MyRecipeResponse> = MutableLiveData()
    val getUserRecipeResponse get() = _getUserRecipeResponse

    fun getMyRecipe(userId : Int, pageNumber : Int, pageSize : Int) {
        CompositeDisposable().add(
            remoteRepository.getUserMyRecipes(userId, pageNumber, pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { response ->
                        getUserRecipeResponse.postValue(response)
                    }, { throwable ->
                        Log.d(TAG,"throwable.localizedMessage${throwable.localizedMessage}")
                        getUserRecipeResponse.postValue(null)
                    })
        )
    }

    companion object{
        const val TAG = "UserPageMyRecipeViewModel"
    }
}