package Entities;

import Utils.ValidationUtils;

public class Address {
    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;

    public Address() {
    }

    public Address(String street, int number, String complement, String neighborhood, String zipCode, String city, String state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public void setStreet(String street) {
        ValidationUtils.validateRequiredString(street, "Rua");
        this.street = street;
    }

    public void setNumber(int number) {
        ValidationUtils.validateRequiredInteger(number, "Número");
        this.number = number;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setNeighborhood(String neighborhood) {
        ValidationUtils.validateRequiredString(neighborhood, "Bairro");
        this.neighborhood = neighborhood;
    }

    public void setZipCode(String zipCode) {
        ValidationUtils.validateRequiredString(zipCode, "CEP");
        if(zipCode.length() != 8){
            throw new IllegalArgumentException("CEP '" + zipCode + "' precisa ter 8 digitos");
        }
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        ValidationUtils.validateRequiredString(city, "Cidade");
        this.city = city;
    }

    public void setState(String state) {
        ValidationUtils.validateRequiredString(state, "Estado");
        this.state = state;
    }

    @Override
    public String toString(){
        return ("Rua: " + street +
                "\nNúmero: " + number +
                "\nComplemento: " + complement +
                "\nBairro: " + neighborhood +
                "\nCEP: " + zipCode +
                "\nCidade: " + city +
                "\nEstado: " + state);
    }
}
