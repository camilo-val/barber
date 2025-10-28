package com.barber.productservice.infrastructure.drivenadapter.mongo.document;

import com.barber.productservice.domain.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDocument {
    @Id
    private String id;
    private String categoryName;
    private boolean status;
    private List<String> products;
    private Date createAt;
}
