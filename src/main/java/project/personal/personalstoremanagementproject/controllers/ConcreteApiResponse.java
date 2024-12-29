package project.personal.personalstoremanagementproject.controllers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcreteApiResponse<T> extends AbstractApiResponse<T> {
}