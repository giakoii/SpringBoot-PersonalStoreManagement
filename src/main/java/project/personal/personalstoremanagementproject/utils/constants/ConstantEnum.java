package project.personal.personalstoremanagementproject.utils.constants;

public class ConstantEnum {
    public enum Role {
        CUSTOMER,
        ADMIN,
        STAFF
    }

    public enum OrderStatus {
        PAYMENT_PENDING,
        PAYMENT_COMPLETED,
        PACKAGING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    public enum MembershipLevel{
        NORMAL,
        SILVER,
        GOLD,
        PLATINUM
    }
}