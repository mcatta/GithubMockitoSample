package eu.marcocattaneo.githubmockitosample.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import eu.marcocattaneo.githubmockitosample.data.Repository
import eu.marcocattaneo.githubmockitosample.data.UserService
import eu.marcocattaneo.githubmockitosample.utils.LiveDataResult
import io.reactivex.FlowableSubscriber
import io.reactivex.MaybeObserver
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscription

class MainViewModel(
    private val userService: UserService
) : ViewModel() {

    val repositoriesLiveData = MutableLiveData<LiveDataResult<List<Repository>>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    /**
     * Request user's repositories
     * @param githubUser Github usename
     */
    fun getRepositories(githubUser: String) {
        this.setLoadingVisibility(true)
        this.userService.getRepositories(githubUser).subscribe(GetRepositoriesConsumer())
    }

    /**
     * Set a progress dialog visible on the View
     * @param visible visible or not visible
     */
    fun setLoadingVisibility(visible: Boolean) {
        loadingLiveData.postValue(visible)
    }

    inner class GetRepositoriesConsumer : MaybeObserver<List<Repository>> {
        override fun onSubscribe(d: Disposable) {
            this@MainViewModel.repositoriesLiveData.postValue(LiveDataResult.loading())
        }

        override fun onError(e: Throwable) {
            this@MainViewModel.repositoriesLiveData.postValue(LiveDataResult.error(e))
        }

        override fun onSuccess(t: List<Repository>) {
            this@MainViewModel.repositoriesLiveData.postValue(LiveDataResult.succes(t))
        }

        override fun onComplete() {
            this@MainViewModel.setLoadingVisibility(false)
        }

    }
}
