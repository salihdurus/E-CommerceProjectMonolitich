package com.turkcellGY.ecommerce.business.concretes;

import com.turkcellGY.ecommerce.business.abstracts.ProductService;
import com.turkcellGY.ecommerce.business.dto.requests.create.CreateProductRequest;
import com.turkcellGY.ecommerce.business.dto.requests.update.UpdateProductRequest;
import com.turkcellGY.ecommerce.business.dto.responses.create.CreateProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetAllProductsResponse;
import com.turkcellGY.ecommerce.business.dto.responses.get.GetProductResponse;
import com.turkcellGY.ecommerce.business.dto.responses.update.UpdateProductResponse;
import com.turkcellGY.ecommerce.core.exceptions.BusinessException;
import com.turkcellGY.ecommerce.entities.Product;
import com.turkcellGY.ecommerce.entities.enums.Status;
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
    public List<GetAllProductsResponse> getAll(boolean includePassive) {
        List<Product> cars = filterProductsByStatus(includePassive);
        List<GetAllProductsResponse> response = cars.stream().map(product -> mapper.map(product, GetAllProductsResponse.class)).toList();
        return response;
    }

    @Override
    public GetProductResponse getById(UUID id) {
        checkIfProductExists(id);
        GetProductResponse response = mapper.map(repository.findById(id).orElseThrow(), GetProductResponse.class);
        return response;
    }

    @Override
    public CreateProductResponse add(CreateProductRequest request) {
        Product product = mapper.map(request, Product.class);
        product.setId(null);
        product.setStatus(Status.Active);
        CreateProductResponse response = mapper.map(repository.save(product), CreateProductResponse.class);
        return response;
    }

    @Override
    public UpdateProductResponse update(UUID id, UpdateProductRequest request) {
        checkIfProductExists(id);
        Product product = mapper.map(request, Product.class);
        product.setId(id);
        UpdateProductResponse response = mapper.map(repository.save(product), UpdateProductResponse.class);
        return response;
    }

    @Override
    public void delete(UUID id) {
        checkIfProductExists(id);
        repository.deleteById(id);
    }

    @Override
    public void setStatus(UUID id, Status status) {
        checkIfProductExists(id);
        Product product = repository.findById(id).orElseThrow();
        product.setStatus(status);
        repository.save(product);
    }

    private List<Product> filterProductsByStatus(boolean includePassive) {
        if (includePassive) {
            return repository.findAll();
        }
        return repository.findAllByStatusIsNot(Status.Passive);
    }

    private void checkIfProductExists(UUID id) {
        if (!repository.existsById(id)) {
            throw new BusinessException("Ürün Bulunamadı !");
        }
    }
}
