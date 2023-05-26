package com.turkcellGY.ecommerce.business.dto.responses.create;

import com.turkcellGY.ecommerce.entities.enums.Status;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private UUID id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    private Status status;
}
