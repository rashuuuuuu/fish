package com.website.admin.product.service.impl;
import com.website.admin.product.mapper.ProductMapper;
import com.website.admin.product.model.*;
import com.website.admin.product.service.ProductService;
import com.website.common.constant.ProductStatusConstant;
import com.website.common.constant.StatusConstant;
import com.website.common.dto.*;
import com.website.common.repo.ProductStatusRepository;
import com.website.common.repo.StatusRepository;
import com.website.common.service.SearchResponse;
import com.website.common.util.ResponseUtil;
import com.website.entity.product.Product;
import com.website.repository.ProductRepository;
import com.website.repository.ProductSearchRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductStatusRepository productStatusRepository;
    private final ProductMapper productMapper;
    private final SearchResponse searchResponse;
    private final ProductSearchRepository productSearchRepository;

    @Override
    @Transactional
    public Mono<ApiResponse<?>> createProductService(CreateProductService createProductService, Principal connectedUser) {
        Product product = productRepository.findByName(createProductService.getName());
        if (product == null) {
            Product product1 = productMapper.mapToEntity(createProductService);
            productRepository.save(product1);
//        adminActivityService.logActivity(connectedUser, "Create", "Product", product.get().getName());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product created successfully"));
        }
        return Mono.just(ResponseUtil.getFailureResponse("The product is already registered"));
    }

    @Override
    public Mono<ApiResponse<?>> getProductList(SearchParam searchParam) {
        SearchResponseWithMapperBuilder<Product, SearchProductResponse> responseBuilder = SearchResponseWithMapperBuilder.<Product, SearchProductResponse>builder()
                .count(productSearchRepository::count)
                .searchData(productSearchRepository::getAll)
                .mapperFunction(this.productMapper::getProductSearchResponse)
                .searchParam(searchParam)
                .build();
        PageableResponse<SearchProductResponse> response = searchResponse.getSearchResponse(responseBuilder);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse(response, "Product list fetched successfully"));
    }

    @Override
    public Mono<ApiResponse<?>> viewProductDetail(ViewProductRequest viewProductRequest) {
        Optional<Product> product = Optional.ofNullable(productRepository.findByCode(viewProductRequest.getCode()));
            if (product.isEmpty() || StatusConstant.DELETED.getName().equals(product.get().getStatusProduct().getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
            }
            ProductDto productDto = productMapper.viewProductDetail(product.get());
            return Mono.just(ResponseUtil.getSuccessfulApiResponse(productDto, "Product detail fetched successfully"));
        }


        @Override
    @Transactional
    public Mono<ApiResponse<?>> updateProduct(UpdateProductRequest updateProductRequest) {
            Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findByCode(updateProductRequest.getCode()));
            if (optionalProduct.isEmpty() || optionalProduct.get().getStatusProduct().getName().equals(StatusConstant.DELETED.getName())) {
                return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
            }
        Product product = productMapper.updateProduct(optionalProduct.get(), updateProductRequest);
        productRepository.save(product);
            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product updated successfully"));
        }

    @Override
    @Transactional
    public Mono<ApiResponse<?>> deleteProduct(DeleteProductRequest deleteProductRequest) {
        Optional<Product> optionalProduct = Optional.ofNullable(productRepository.findByCode(deleteProductRequest.getCode()));
        if (optionalProduct.isEmpty() || ProductStatusConstant.DELETED.getName().equals(optionalProduct.get().getStatusProduct().getName())) {
            return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
        }
        Product product = optionalProduct.get();
        product.setStatusProduct(productStatusRepository.findByName(ProductStatusConstant.DELETED.getName()));
        productRepository.save(product);
        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product deleted successfully"));
    }
//    @Override
//    @Transactional
//    public Mono<ApiResponse<?>> blockProduct(BlockProductRequest blockProductRequest, Principal connectedUser) {
//        Optional<Product> blockProduct = productRepository.findByCode(blockProductRequest.getCode());
//        if (blockProduct.isEmpty() || StatusConstant.DELETED.getName().equals(blockProduct.get().getStatusproduct().getName())) {
//            return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
//        }
//        if (StatusConstant.BLOCKED.getName().equals(blockProduct.get().getStatusproduct().getName())) {
//            return Mono.just(ResponseUtil.getNotFoundResponse("Product is already Blocked"));
//        }
//        Product product = blockProduct.get();
//        product.setStatusproduct(statusRepository.findByName(StatusConstant.BLOCKED.getName()));
//        adminActivityService.logActivity(connectedUser, "Block", "Product", product.getName());
//        productLogMapper.mapToBlockEntity(product, blockProductRequest, connectedUser);
//        productRepository.save(product);
//        return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product blocked successfully"));
//    }
//
//    @Override
//    @Transactional
//    public Mono<ApiResponse<?>> unBlockProduct(UnblockProductRequest unblockProductRequest, Principal connectedUser) {
//        Optional<Product> blockProduct = productRepository.findByCode(unblockProductRequest.getCode());
//        if (blockProduct.isEmpty() || StatusConstant.DELETED.getName().equals(blockProduct.get().getStatusproduct().getName())) {
//            return Mono.just(ResponseUtil.getNotFoundResponse("Product not found"));
//        }
//        if (StatusConstant.BLOCKED.getName().equals(blockProduct.get().getStatusproduct().getName())) {
//            Product product = blockProduct.get();
//            product.setStatusproduct(statusRepository.findByName(StatusConstant.ACTIVE.getName()));
//            adminActivityService.logActivity(connectedUser, "Unblock", "Product", product.getName());
//            productLogMapper.mapToUnblock(product, unblockProductRequest, connectedUser);
//            productRepository.save(product);
//            return Mono.just(ResponseUtil.getSuccessfulApiResponse("Product unblocked successfully"));
//        }
//        return Mono.just(ResponseUtil.getNotFoundResponse("Product is not blocked so cannot be unblocked!"));
//
//    }
}


