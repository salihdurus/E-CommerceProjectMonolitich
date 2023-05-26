package com.turkcellGY.ecommerce.business.dto.requests.update;

import com.turkcellGY.ecommerce.entities.enums.Status;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    @Min(0)
    private int quantity;
    @Min(1)
    private double price;
    @Size(min = 10, max = 50)
    private String description;
    @NotNull
    private Status status;
}
