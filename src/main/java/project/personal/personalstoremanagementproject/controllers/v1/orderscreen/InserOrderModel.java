package project.personal.personalstoremanagementproject.controllers.v1.orderscreen;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class InserOrderModel {
    Long orderId;
}
