package br.ufes.pi.trabalho.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Embeddable
public class Address{
    private String street;
    private String city;
    private String district;
    private Long number;

    public Address(String street, String city, String district, Long number){
        this.street = street;
        this.city = city;
        this.district = district;
        this.number = number;
    }

    protected Address(){}

    public String getDistrict() {
        return district;
    }
    public String getCity() {
        return city;
    }
    public Long getNumber() {
        return number;
    }
    public String getStreet() {
        return street;
    }

}