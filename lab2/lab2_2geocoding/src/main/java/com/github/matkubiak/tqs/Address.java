package com.github.matkubiak.tqs;

import java.util.Objects;

public class Address {
    public Address(String road, String houseNumber, String zip, String city) {
        this.road = road;
        this.houseNumber = houseNumber;
        this.zip = zip;
        this.city = city;
    }

    private String road;
    private String houseNumber;
    private String zip;
    private String city;

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!road.equals(address.road)) return false;
        if (!Objects.equals(houseNumber, address.houseNumber)) return false;
        if (!Objects.equals(zip, address.zip)) return false;
        return Objects.equals(city, address.city);
    }

    @Override
    public String toString() {
        return "Address{" +
                "road='" + road + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zip='" + zip + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = road.hashCode();
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        return result;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getRoad() {
        return road;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setZio(String zio) {
        this.city = city;
    }

    public String getZio() {
        return "";
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
