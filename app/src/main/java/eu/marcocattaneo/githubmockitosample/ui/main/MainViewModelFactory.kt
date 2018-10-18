package eu.marcocattaneo.githubmockitosample.ui.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import eu.marcocattaneo.githubmockitosample.data.UserService

class MainViewModelFactory(
    private val userService: UserService
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
            return MainViewModel(this.userService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}