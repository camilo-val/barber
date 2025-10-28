package com.barber.productservice.domain.model.category;

import com.barber.productservice.domain.model.exceptions.BusinessExceptions;
import com.barber.productservice.domain.model.exceptions.BusinessMessageExceptions;
import lombok.Getter;

import java.util.Date;

@Getter
public class Category {
    private final String id;
    private final String categoryName;
    private final Boolean status;
    private final Date createAt;

    private Category(String id, String categoryName, Boolean status, Date createAt) {
        this.id = id;
        this.categoryName = categoryName;
        this.status = status;
        this.createAt = createAt;
    }

    public static Category newCategory (String id, String categoryName, Boolean status, Date createAt){
        if (categoryName == null || categoryName.isBlank()){
            throw new BusinessExceptions(BusinessMessageExceptions.PRODUCT_EXIST);
        }
        return new Category(id,categoryName,status,createAt);
    }
}
