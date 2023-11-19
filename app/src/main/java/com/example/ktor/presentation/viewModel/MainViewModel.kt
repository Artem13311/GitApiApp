package com.example.ktor.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ktor.network.repository.GitRepoRepository
import com.example.ktor.data.Resource
import com.example.ktor.network.model.RawGitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GitRepoRepository
):ViewModel(){
    private val _gitRepository = MutableStateFlow<Resource<RawGitRepository>?>(null)
    val gitRepository:StateFlow<Resource<RawGitRepository>?> =_gitRepository

    init{
        viewModelScope.launch {
            _gitRepository.value = Resource.Loading
            _gitRepository.value = repository.getGitRepo()

        }
    }
}