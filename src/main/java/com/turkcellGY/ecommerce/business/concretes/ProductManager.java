package com.turkcellGY.ecommerce.business.concretes;

import com.turkcellGY.ecommerce.business.abstracts.ProductService;
import com.turkcellGY.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.turkcellGY.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.turkcellGY.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.update.UpdateProductResponse;
import com.turkcellGY.ecommerce.entity.Product;
import com.turkcellGY.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private final ProductRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllProductsResponse> getAll() {
        List<GetAllProductsResponse> response = repository.findAll().stream().map(product -> mapper.map(product, GetAllProductsResponse.class)).toList();
        return response;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        GetProductResponse response = mapper.map(repository.findById(id).orElseThrow(), GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        product.setId(null);
        CreateProductResponse response = mapper.map(repository.save(product), CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        Product product=mapper.map(request, Product.class);
        product.setId(id);
        UpdateProductResponse response=mapper.map(repository.save(product), UpdateProductResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
