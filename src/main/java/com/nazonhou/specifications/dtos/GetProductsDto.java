package com.nazonhou.specifications.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsDto {
    private String name;
    private Integer height;
    private Integer width;
    private Integer weight;
    private String color;
    private UUID categoryId;
}
