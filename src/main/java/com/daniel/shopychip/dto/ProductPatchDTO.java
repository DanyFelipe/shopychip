package com.daniel.shopychip.dto;
import com.daniel.shopychip.model.ProductCondition;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPatchDTO {
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private ProductCondition condition;
    private List<String> picturesUrls;
    private String location;
}