package eu.marcocattaneo.githubmockitosample.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.marcocattaneo.githubmockitosample.GithubApplication
import eu.marcocattaneo.githubmockitosample.R
import eu.marcocattaneo.githubmockitosample.data.Repository
import eu.marcocattaneo.githubmockitosample.data.UserService
import eu.marcocattaneo.githubmockitosample.utils.LiveDataResult

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val dataObserver = Observer<LiveDataResult<List<Repository>>> { result ->
        when (result?.status) {
            LiveDataResult.Status.LOADING -> {
                // Loading data
            }

            LiveDataResult.Status.ERROR -> {
                // Error for http request
            }

            LiveDataResult.Status.SUCCESS -> {
                // Data from API
            }
        }
    }

    private val loadingObserver = Observer<Boolean> { visibile ->
        // Show hide a progress
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // ViewModel factory
        val factory = MainViewModelFactory((activity?.application as GithubApplication).retrofit.create(UserService::class.java))

        // Create ViewModel and bind observer
        this.viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        this.viewModel.repositoriesLiveData.observe(this, this.dataObserver)
        this.viewModel.loadingLiveData.observe(this, this.loadingObserver)
    }

}
