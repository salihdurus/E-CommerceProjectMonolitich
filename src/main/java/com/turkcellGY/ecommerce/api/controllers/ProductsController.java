package com.turkcellGY.ecommerce.api.controllers;

import com.turkcellGY.ecommerce.business.abstracts.ProductService;
import com.turkcellGY.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.turkcellGY.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.turkcellGY.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.update.UpdateProductResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {
    private final ProductService service;

    @GetMapping
    public List<GetAllProductsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateProductResponse add(@Valid @RequestBody CreateProductRequest request) {
        return service.add(request);
    }

    @PutMapping("/{id}")
    public UpdateProductResponse update(@PathVariable UUID id,@Valid @RequestBody UpdateProductRequest request) {
        return service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
