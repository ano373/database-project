package com.example.jobopening_v1;

import java.time.LocalDate;
import java.util.Arrays;

public class memberDATA {
    private int id;
    private int companyId;
    private String userType;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String sex;
    private String country;
    private String city;
    private LocalDate birthday;
    private int phoneNumber;
    private byte[] profilePic;
    private String position;
    private int salary;

    public memberDATA(int companyId, String userType, String firstName, String lastName, String email,
                  String password, String sex, String country, String city, LocalDate birthday,
                  int phoneNumber, byte[] profilePic, String position) {
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
    }


    public int getId() {
        return id;
    }

    // Getter for First Name
    public String getFirstName() {
        return firstName;
    }

    // Getter for Last Name
    public String getLastName() {
        return lastName;
    }

    // Getter for Email
    public String getEmail() {
        return email;
    }

    // Getter for Phone Number
    public int getPhoneNumber() {
        return phoneNumber;
    }

    // Getter for User Type
    public String getUserType() {
        return userType;
    }

    // Getter for Position
    public String getPosition() {
        return position;
    }

    // Getter for Salary (assuming it's a property of Member)
    public double getSalary() {

        return salary;
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
