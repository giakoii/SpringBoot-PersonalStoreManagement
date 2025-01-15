package project.personal.personalstoremanagementproject.repositories;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import project.personal.personalstoremanagementproject.entities.Order;

public interface OrderRepository extends BaseRepository<Order, Long> {
    @Procedure(name = "CreateOrder")
    Object[] createOrder(
            @Param("CustomerId") Long customerId,
            @Param("CreatedBy") Long createBy,
            @Param("OrderDetails") String orderDetails
    );
}