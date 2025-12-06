package br.com.gabriel.araujo_pay.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CustomerRequest {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    private String document;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setDocument(String document) {
        this.document = document;
    }
}
