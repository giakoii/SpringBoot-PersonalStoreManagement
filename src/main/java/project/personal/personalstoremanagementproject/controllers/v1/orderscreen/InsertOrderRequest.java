package project.personal.personalstoremanagementproject.controllers.v1.orderscreen;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.controllers.AbstractApiRequest;

import java.util.List;

@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class InsertOrderRequest extends AbstractApiRequest {
    Long userId;
    Long createById;
    List<ListOrderDetail> orderDetail;
}
