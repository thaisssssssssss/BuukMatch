package br.ufes.pi.trabalho.dto;


public class AddressRequest {
    private String street;
    private String district;
    private String city;
    private Long number;

    public AddressRequest(String street, String district, String city, Long number){
        this.street = street; 
        this.district = district;
        this.city = city;
        this.number =  number;
    }

    public AddressRequest(){}

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public Long getNumber() {
        return number;
    }
}
