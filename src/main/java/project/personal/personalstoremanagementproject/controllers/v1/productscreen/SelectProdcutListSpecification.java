package project.personal.personalstoremanagementproject.controllers.v1.productscreen;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class SelectProdcutListSpecification {
    String specificationKey;

    String specificationValue;
}
