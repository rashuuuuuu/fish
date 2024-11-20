package com.website.admin.product.controller;
import com.website.admin.product.model.*;
import com.website.admin.product.service.ProductService;
import com.website.common.constant.ApiConstant;
import com.website.common.dto.ApiResponse;
import com.website.common.dto.SearchParam;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping(ApiConstant.PRODUCT)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(ApiConstant.CREATE)
    public Mono<ApiResponse<?>> createProduct(@RequestBody @Valid CreateProductService createProductService,
                                              Principal connectedUser){
        return productService.createProductService(createProductService, connectedUser);
    }
    @PostMapping(ApiConstant.GET)
    public Mono<ApiResponse<?>> getProductList(@RequestBody @Valid SearchParam searchParam){
        return productService.getProductList(searchParam);
    }
    @PostMapping(ApiConstant.DETAIL)
    public Mono<ApiResponse<?>> viewProductDetail(@RequestBody @Valid ViewProductRequest viewProductRequest){
        return productService.viewProductDetail(viewProductRequest);
    }
    @PostMapping(ApiConstant.UPDATE)
    public Mono<ApiResponse<?>> updateProduct(@RequestBody @Valid UpdateProductRequest updateProductRequest){
        return productService.updateProduct(updateProductRequest);
    }
    @PostMapping(ApiConstant.DELETE)
    public Mono<ApiResponse<?>> deleteProduct(@RequestBody @Valid DeleteProductRequest deleteProductRequest){
        return productService.deleteProduct(deleteProductRequest);
    }
//    @PostMapping(ApiConstant.BLOCK)
//    public Mono<ApiResponse<?>> blockProduct(@RequestBody @Valid BlockProductRequest blockProductRequest, Principal connectedUser){
//        return productService.blockProduct(blockProductRequest,connectedUser);
//    }
//
//    @PostMapping(ApiConstant.UNBLOCK)
//    public Mono<ApiResponse<?>> unBlockProduct(@RequestBody @Valid UnblockProductRequest unblockProductRequest, Principal connectedUser){
//        return productService.unBlockProduct(unblockProductRequest, connectedUser);
//    }

}
