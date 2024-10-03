package com.raouf.ecommerceapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raouf.domain.useCase.GetProductById
import com.raouf.domain.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.qualifier._q


class DetailScreenViewModel(
    private val getProductById: GetProductById
) : ViewModel() {


    private val _detailState = MutableStateFlow(DetailScreenState())
    val detailState = _detailState.asStateFlow()

    fun getProduct(id: Int) {
        viewModelScope.launch {
            getProductById.invoke(id).let { result ->
                when (result) {

                    is Resource.Error -> {
                        _detailState.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    }

                    is Resource.IsLoading -> {
                        _detailState.update {
                            it.copy(
                                isLoading = result.isLoading
                            )
                        }
                    }

                    is Resource.Success -> {
                        val data = result.data
                        data?.let {
                            _detailState.update {
                                it.copy(
                                    selectedProduct = data
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}