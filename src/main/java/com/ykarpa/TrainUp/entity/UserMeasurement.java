package com.ykarpa.TrainUp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "user_measurements")
public class UserMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double height; // у см
    private Double weight; // у кг

    private Date date;

    @ElementCollection
    private List<AdditionalMeasurement> additionalMeasurements = new ArrayList<>();

    @Embeddable
    public static class AdditionalMeasurement {
        private String name;
        private String value;
    }
}
