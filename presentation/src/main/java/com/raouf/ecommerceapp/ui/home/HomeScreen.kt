package com.raouf.ecommerceapp.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen( viewModel: HomeViewModel = koinViewModel()) {

    Text(
        text = viewModel.uiState.collectAsState().value.productsList.size.toString(),
        fontSize = 32.sp
    )


}