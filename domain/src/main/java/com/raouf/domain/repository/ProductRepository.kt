package com.raouf.domain.repository

import com.raouf.domain.model.Product
import com.raouf.domain.util.Resource

interface ProductRepository  {
    fun getProduct () : Resource<List<Product>>
}