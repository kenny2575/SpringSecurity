package com.example.springsecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Persons {
    @EmbeddedId
    private BasePerson basePerson;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "city_of_living")
    private String city;
}

