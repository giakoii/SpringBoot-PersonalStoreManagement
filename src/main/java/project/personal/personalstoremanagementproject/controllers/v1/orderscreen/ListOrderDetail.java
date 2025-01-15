package project.personal.personalstoremanagementproject.controllers.v1.orderscreen;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ListOrderDetail {
    Long productId;
    Integer quantity;
}
