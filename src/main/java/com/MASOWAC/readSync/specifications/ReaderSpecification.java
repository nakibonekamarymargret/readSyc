package com.MASOWAC.readSync.specifications;

import com.MASOWAC.readSync.models.Reader;
import org.springframework.data.jpa.domain.Specification;

public class ReaderSpecification {
    public static Specification<Reader> byField(String field, String value) {
        switch (field.toLowerCase()) {
            case "email":
                return (root, query, builder) -> builder.equal(root.get("email"), value);
            case "firstName":
                return (root, query, builder) -> builder.equal(root.get("firstName"), value);
            case "lastName":
                return (root, query, builder) -> builder.equal(root.get("lastName"), value);
            case "phoneNumber":
                return (root, query, builder) -> builder.equal(root.get("phoneNumber"), value);
            case "address":
                return (root, query, builder) -> builder.equal(root.get("address"), value);
            default:
                return null; // Handle the case when the field is not recognized
        }
    }
}
