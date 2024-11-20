package com.website.admin.product.service;

import com.website.admin.product.model.*;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface ProductService {
    Mono<ApiResponse<?>> createProductService(CreateProductService createProductService, Principal connectedUser);
    Mono<ApiResponse<?>> getProductList(SearchParam searchParam);
    Mono<ApiResponse<?>> viewProductDetail(ViewProductRequest viewProductRequest);
    Mono<ApiResponse<?>> updateProduct(UpdateProductRequest updateProductRequest);
    Mono<ApiResponse<?>> deleteProduct(DeleteProductRequest deleteProductRequest);
//    Mono<ApiResponse<?>> blockProduct(BlockProductRequest blockProductRequest, Principal connectedUser);
//    Mono<ApiResponse<?>> unBlockProduct(UnblockProductRequest unblockProductRequest, Principal connectedUser);

}
