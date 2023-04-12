package application.zpo_lab4;
// application.zpo_lab4.Person
public class Person {
    public Person() {
        this("Imie","Nazwisko",40,50.,"111111111","Opis",false);
    }

    public Person(String name, String surName, Integer age, Double weight, String phoneNumber, String textDescription, boolean employed) {
        this.name = name;
        this.surName = surName;
        this.age = age;
        this.weight = weight;
        this.phoneNumber = phoneNumber;
        this.textDescription = textDescription;
        this.employed = employed;
    }

    private String name;
    private String surName;
    private Integer age;
    private Double weight;
    private String phoneNumber;
    private Boolean employed;
    private String textDescription;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public Boolean getEmployed() {
        return employed;
    }

    public void setEmployed(Boolean employed) {
        this.employed = employed;
    }
}
