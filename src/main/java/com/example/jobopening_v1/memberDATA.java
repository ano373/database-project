package com.example.jobopening_v1;

import java.time.LocalDate;
import java.util.Arrays;

public class memberDATA {
    private int id;
    private int companyId;
    private String userType;
    private String firstName;
    private String lastName;

    private String FullName;
    private String email;
    private String password;
    private final String sex;
    private String country;
    private String city;
    private LocalDate birthday;
    private int phoneNumber;
    private byte[] profilePic;
    private String position;
    private int salary;

    public memberDATA(int companyId, String userType, String firstName, String lastName, String email,
                  String password, String sex, String country, String city, LocalDate birthday,
                  int phoneNumber, byte[] profilePic, String position,int salary) {
        this.companyId = companyId;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.profilePic = profilePic;
        this.position = position;
        this.salary = salary;
    }
    // no profile pic
    public memberDATA(int companyId, String userType, String firstName, String lastName, String email,
                      String password, String sex, String country, String city, LocalDate birthday,
                      int phoneNumber, String position,int salary) {
        this.companyId = companyId;
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.salary = salary;
    }

    public memberDATA(int id,String firstName, String lastName , String email, String sex, String city,
                      int phoneNumber, String position) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sex = sex;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.position = position;
    }


    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return firstName + ' ' + lastName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public String getUserType() {
        return userType;
    }
    public String getPosition() {
        return position;
    }
    public String getCity(){
        return city;
    }

    public String getSex(){ return sex;}
    public double getSalary() {
        return salary;
    }

    public Integer getCompanyIDForEmployer() {
        if ("employer".equalsIgnoreCase(userType)) {
            return companyId;
        }
        return null;
    }



    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", userType='" + userType + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", birthday=" + birthday +
                ", phoneNumber=" + phoneNumber +
                ", profilePic=" + Arrays.toString(profilePic) +
                ", position='" + position + '\'' +
                '}';
    }
}
