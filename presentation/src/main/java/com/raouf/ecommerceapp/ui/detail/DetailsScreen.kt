package com.raouf.ecommerceapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.raouf.domain.model.Product
import com.raouf.ecommerceapp.ui.theme.orange

@Composable
fun DetailScreen(
    id: Int,
    state: DetailScreenState,
    onEvent: (DetailScreenEvents) -> Unit
) {
    onEvent(DetailScreenEvents.GetProduct(id = id))
    val product = state.selectedProduct?.let { product ->
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagesView(
                state = state,
                onEvent = onEvent,
                product = product
            )
            ReviewView(product = product)

        }
    }
}


@Composable
fun ImagesView(
    state: DetailScreenState,
    onEvent: (DetailScreenEvents) -> Unit,
    product: Product
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val imageState = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(product.images[state.selectedImageIndex])
                .size(Size.ORIGINAL)
                .build()
        ).state

        if (imageState is AsyncImagePainter.State.Success) {
            Image(
                painter = imageState.painter,
                contentDescription = null,
                modifier = Modifier.size(300.dp),
                contentScale = ContentScale.Crop
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            product.images.forEachIndexed { index, image ->
                val imagesState = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .size(Size.ORIGINAL)
                        .build()
                ).state
                if (imagesState is AsyncImagePainter.State.Success) {
                    Image(
                        painter = imagesState.painter,
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                onEvent(DetailScreenEvents.SelectImage(index))
                            }
                            .then(
                                if (index == state.selectedImageIndex) {
                                    Modifier.border(
                                        2.dp,
                                        Color.Black,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                } else
                                    Modifier
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
                if (imagesState is AsyncImagePainter.State.Loading) {
                    Box(
                        modifier = Modifier.size(75.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

    }
}

@Composable
fun ReviewView(
    product: Product
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
         Icon(
             imageVector = Icons.Rounded.Star,
             contentDescription = null,
             tint = orange,
             modifier = Modifier.size(26.dp),
         )
        Text(
            text = product.rating.toString(),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = orange
        )

    }

}

