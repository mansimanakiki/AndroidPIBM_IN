package com.ramanbyte.pibm_in.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramanbyte.pibm_in.data.model.BannerItem
import com.ramanbyte.pibm_in.data.model.NavigationItem
import com.ramanbyte.pibm_in.data.model.PibmInfo
import com.ramanbyte.pibm_in.data.repository.PibmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PibmRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadData()
    }
    
    private fun loadData() {
        viewModelScope.launch {
            try {
                _uiState.value = HomeUiState.Loading
                
                // Refresh data from Firebase Remote Config
                repository.refreshData()
                
                // Collect navigation items from Room
                repository.navigationItems.collect { items ->
                    val banners = repository.getBanners()
                    val pibmInfo = repository.getPibmInfo()
                    
                    _uiState.value = HomeUiState.Success(
                        banners = banners,
                        pibmInfo = pibmInfo,
                        navigationItems = items
                    )
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
    
    fun retry() {
        loadData()
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val banners: List<BannerItem>,
        val pibmInfo: PibmInfo,
        val navigationItems: List<NavigationItem>
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
