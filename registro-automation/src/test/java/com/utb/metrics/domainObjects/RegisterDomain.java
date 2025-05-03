package com.utb.metrics.domainObjects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class RegisterDomain {
    private String documentNumber;
    private String name;
    private String lastName;
    private String cellPhone;
    private String email;
}
