package com.website.repository;
import com.website.entity.product.Product;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByCode(String code);
    Product findByName(@NotBlank(message = "name cannot be blank") String product);
}