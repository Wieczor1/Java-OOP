public class Address {
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private String streetName;
    private String buildingNumber;
    private String houseNumber;
    private String city;

    public Address(String streetName, String buildingNumber, String houseNumber, String city) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
