package project.personal.personalstoremanagementproject.controllers;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import project.personal.personalstoremanagementproject.exceptions.DetailError;
import project.personal.personalstoremanagementproject.exceptions.ErrorCode;

import java.util.List;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConcreteApiResponse<T> extends AbstractApiResponse<T> {
}