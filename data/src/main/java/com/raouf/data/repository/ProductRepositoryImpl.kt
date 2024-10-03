package com.raouf.data.repository

import com.raouf.data.remote.NetworkService
import com.raouf.data.toProduct
import com.raouf.domain.model.Product
import com.raouf.domain.repository.ProductRepository
import com.raouf.domain.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okio.IOException
import retrofit2.HttpException

class ProductRepositoryImpl (
    private val networkService: NetworkService
) : ProductRepository {



    override suspend fun getProductList(
        category : String
    ):Flow<Resource<List<Product>>>{
        return flow {
            emit(Resource.IsLoading(true))
            val data = try {
                networkService.getProducts(categoryName = category)
            } catch (e : IOException){
                e.printStackTrace()
                return@flow
            }catch (e : HttpException){
                e.printStackTrace()
                return@flow
            }catch (e : Exception){
                e.printStackTrace()
                return@flow
            }
            emit(Resource.Success(
                data = data.products.map {
                    it.toProduct()
                }
            ))
            emit(Resource.IsLoading(false))
        }.flowOn(Dispatchers.IO)
    }


    override suspend fun getProductById(id : Int): Resource<Product> {
        val product = try {
            networkService.getProductById(id = id)
        }catch (e : HttpException ){
            e.printStackTrace()
            return Resource.Error(message = e.message)
        }catch (e : IOException){
            e.printStackTrace()
            return Resource.Error(message = e.message)
        }catch (e : Exception){
            e.printStackTrace()
            return Resource.Error(message = e.message)
        }

        return Resource.Success(
            data = product.toProduct()
        )
    }

}