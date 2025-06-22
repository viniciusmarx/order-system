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

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
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
        return ("Rua: " + street + " Número: " + number +
                "\nComplemento: " + complement +
                "\nCEP: " + zipCode + " Bairro: " + neighborhood +
                "\nCidade: " + city + " Estado: " + state);
    }
}
