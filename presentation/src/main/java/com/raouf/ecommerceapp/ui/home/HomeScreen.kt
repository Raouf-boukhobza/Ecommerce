package com.raouf.ecommerceapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.raouf.domain.model.Product
import com.raouf.domain.util.Category
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val state = viewModel.uiState.collectAsState().value


    Column {
        Text(
            text = "Discover Products",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.padding(start = 8.dp, bottom = 12.dp)
        )
        val categories = listOf(
            Category.MenWatches,
            Category.Tops,
            Category.HomeDecoration,
            Category.Sport,
            Category.Vehicle,
            Category.Shoes,
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.width(0.dp))
            }
            items(categories) { category ->
                Button(
                    onClick = {
                        viewModel.changeCategory(category = category)
                        viewModel.getProducts()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (category == state.category) Color.Black
                        else Color.LightGray,
                    )
                ) {
                    Text(
                        text = category.name,
                        fontSize = 16.sp,
                        fontStyle = FontStyle.Italic,
                        color = if (category == state.category) Color.White
                        else Color.Black
                    )

                }
            }

        }
        if (!state.isLoading) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 150.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.productsList, key = { it.id }) { product ->
                    ProductCard(product)
                }
            }

        }

    }
}


@Composable
fun ProductCard(product: Product) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images[0])
                .size(Size.ORIGINAL)
                .build()
        ).state

        if (imageState is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
        if (imageState is AsyncImagePainter.State.Success) {
            Box {
                Image(
                    painter = imageState.painter,
                    contentDescription = product.title,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Crop
                )
                if (product.discountPercentage > 10) {
                    Text(
                        text = "-${product.discountPercentage}%",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color.Red,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(5.dp)

                    )
                }
            }

        }

        Text(
            text = product.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.fillMaxWidth(0.8f),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            minLines = 2
        )
        Spacer(modifier = Modifier.height(8.dp))


        Row(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                text = "$${product.priceAfterDiscount}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
            if (product.discountPercentage > 10) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "$${product.price}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.width(50.dp),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}