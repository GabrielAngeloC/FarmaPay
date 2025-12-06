package br.com.gabriel.araujo_pay.dto.customer;

public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private String document;

    public CustomerResponse(Long id, String name, String email, String document) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }
}
