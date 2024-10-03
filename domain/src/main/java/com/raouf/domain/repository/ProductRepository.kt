package com.raouf.domain.repository

import com.raouf.domain.model.Product
import com.raouf.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProductRepository  {

   suspend fun getProductList(
      category : String
   ) : Flow<Resource<List<Product>>>

   suspend fun getProductById (
      id : Int
   ) : Resource <Product>

}