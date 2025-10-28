package com.barber.productservice.infrastructure.entrypoint.reactiveweb.mapper;

import com.barber.productservice.domain.model.category.Category;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.category.CategoryRequestDto;
import com.barber.productservice.infrastructure.entrypoint.reactiveweb.dto.category.CategoryResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toDomain(CategoryRequestDto dto);
    CategoryResponseDto toResponse(Category category);

    @ObjectFactory
    default Category createCategory(CategoryRequestDto dto){
        if (dto == null) return null;
        return Category.newCategory(dto.getId(),
                dto.getCategoryName(),dto.isStatus(),
                dto.getCreateAt());
    }
}
