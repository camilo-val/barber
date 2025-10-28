package com.barber.productservice.infrastructure.drivenadapter.mongo.mapper;

import com.barber.productservice.domain.model.category.Category;
import com.barber.productservice.infrastructure.drivenadapter.mongo.document.CategoryDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ObjectFactory;

@Mapper(componentModel = "spring")
public interface CategoryMongoMapper {
    Category toDomain(CategoryDocument document);
    CategoryDocument toDocument(Category category);

    @ObjectFactory
    default Category createCategory(CategoryDocument dto){
        if (dto == null) return null;
        return Category.newCategory(dto.getId(),
                dto.getCategoryName(),dto.isStatus(),
                dto.getCreateAt());
    }
}
