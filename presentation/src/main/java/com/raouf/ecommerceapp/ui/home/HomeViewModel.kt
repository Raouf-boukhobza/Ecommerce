package com.raouf.ecommerceapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raouf.domain.useCase.GetProductsUseCase
import com.raouf.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeScreenState> = MutableStateFlow(HomeScreenState())
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }
    private fun getProducts() {

        viewModelScope.launch {
            getProductsUseCase.execute().collectLatest { result ->

                when (result) {
                    is Resource.Success -> {
                        val productList = result.data
                        productList?.let {
                            _uiState.update {
                                it.copy(
                                    productsList = productList,
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        _uiState.update {
                            it.copy(
                                error = result.message ?: "An Error Occurred"
                            )
                        }
                    }

                    is Resource.IsLoading -> {
                        _uiState.update {
                            it.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }
                }

            }

        }
    }

}