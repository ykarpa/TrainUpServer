package com.ykarpa.TrainUp.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainerWorkoutDTO {
    private Long id;
    private Date date;
    private boolean completed;
    private String clientName;
    private String gymName;

//    public TrainerWorkoutDTO(Long id, LocalDate date, boolean completed, String clientName, String gymName) {
//        this.id = id;
//        this.date = date;
//        this.completed = completed;
//        this.clientName = clientName;
//        this.gymName = gymName;
//    }

    // гетери та сетери
}
