package com.turkcellGY.ecommerce.business.abstracts;

import com.turkcellGY.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.turkcellGY.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.turkcellGY.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.update.UpdateProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    public List<GetAllProductsResponse> getAll();

    public GetProductResponse getById(UUID id);

    public CreateProductResponse add(CreateProductRequest request);

    public UpdateProductResponse update(UUID id,UpdateProductRequest request);

    public void delete(UUID id);
}
