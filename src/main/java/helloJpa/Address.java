package helloJpa;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;
    private String zipcode;
    private String street;


    public Address() {
    }

    public Address(String city, String zipcode, String street) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public String getZipcode() {
        return zipcode;
    }
    public String getStreet() {
        return street;
    }

    private void setCity(String city) {
        this.city = city;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    private void setStreet(String street) {
        this.street = street;
    }
}
