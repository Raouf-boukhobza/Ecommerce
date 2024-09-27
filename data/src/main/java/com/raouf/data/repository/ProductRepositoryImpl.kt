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



    override suspend fun getProduct(
        category : String
    ):Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.IsLoading(true))
            val product =try {
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
                data = product.map {
                    it.toProduct()
                }
            ))
            emit(Resource.IsLoading(false))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getCategories(): List<String> {
        val categories = try {
            networkService.getCategories()
        }catch (e : HttpException ){
            e.printStackTrace()
            return emptyList()
        }catch (e : IOException){
            e.printStackTrace()
            return emptyList()
        }catch (e : Exception){
            e.printStackTrace()
            return emptyList()
        }

        return categories
    }

    override suspend fun getProductById(id : Int): Product? {
        val product = try {
            networkService.getProductById(id = id)
        }catch (e : HttpException ){
            e.printStackTrace()
            return null
        }catch (e : IOException){
            e.printStackTrace()
            return null
        }catch (e : Exception){
            e.printStackTrace()
            return null
        }

        return product.toProduct()
    }

}