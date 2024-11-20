package com.website.admin.product.mapper;

import com.website.admin.product.model.CreateProductService;
import com.website.admin.product.model.SearchProductResponse;
import com.website.admin.product.model.UpdateProductRequest;
import com.website.common.constant.ProductStatusConstant;
import com.website.common.dto.ProductDto;
import com.website.common.repo.ProductStatusRepository;
import com.website.entity.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
@Slf4j
public abstract class ProductMapper {

    @Autowired
    protected ProductStatusRepository productStatusRepository;

    public Product mapToEntity(CreateProductService createProductService){
        Product product = new Product();
        product.setName(createProductService.getName());
        product.setDetails(createProductService.getDetails());
        product.setImage(createProductService.getImage());
        product.setSpecies(createProductService.getSpecies());
        product.setCode(UUID.randomUUID().toString());
        product.setPresent(true);
        product.setRecordedDate(new Date());
        product.setStatusProduct(productStatusRepository.findByName(ProductStatusConstant.AVAILABLE.getName()));
        log.info("log: {}", productStatusRepository.findByName(ProductStatusConstant.AVAILABLE.getName()).getName());
        return product;
    }
    public abstract SearchProductResponse mapToResponse(Product product);
    public List<SearchProductResponse> getProductSearchResponse(List<Product> productList){
        return productList.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    public abstract ProductDto mapToDto(Product product);

    public Product updateProduct(Product product, UpdateProductRequest updateProductRequest){
        product.setName(updateProductRequest.getName());
        product.setDetails(updateProductRequest.getDescription());
        product.setImage(updateProductRequest.getImage());
        return product;

    }
    public abstract ProductDto viewProductDetail(Product education);

//    public abstract ProductDto mapToDto(String details);
}
