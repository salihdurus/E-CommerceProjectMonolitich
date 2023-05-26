package com.turkcellGY.ecommerce.repository;

import com.turkcellGY.ecommerce.entities.Product;
import com.turkcellGY.ecommerce.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    public List<Product> findAllByStatusIsNot(Status status);
    public boolean existsById(UUID id);
}
