package com.raouf.ecommerceapp.ui.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(id: Int , viewModel: DetailScreenViewModel = koinViewModel()) {

    val state = viewModel.detailState.collectAsState().value
    viewModel.getProduct(id = id)
    val product = state.selectedProduct?.let {
        Text(
            text = it.title,
            fontSize = 28.sp,
            color = Color.Gray
        )
    }



}