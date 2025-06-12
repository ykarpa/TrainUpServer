package com.ykarpa.TrainUp.controller;

import com.ykarpa.TrainUp.DTO.TrainerWorkoutDTO;
import com.ykarpa.TrainUp.entity.Exercise;
import com.ykarpa.TrainUp.entity.User;
import com.ykarpa.TrainUp.entity.Workout;
import com.ykarpa.TrainUp.repository.UserRepository;
import com.ykarpa.TrainUp.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/user/{userId}")
    public List<Workout> getWorkoutsByUser(@PathVariable Long userId) {
        return workoutRepository.findByUserIdOrderByDateDesc(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        return workoutRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/trainer/{trainerId}")
    public ResponseEntity<List<TrainerWorkoutDTO>> getTrainerAndClientsWorkouts(@PathVariable Long trainerId) {
        Optional<User> trainerOpt = userRepository.findById(trainerId);

        if (trainerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User trainer = trainerOpt.get();

        List<TrainerWorkoutDTO> result = new ArrayList<>();

        // Тренування самого тренера
        List<Workout> trainerWorkouts = workoutRepository.findByUserId(trainer.getId());
        for (Workout workout : trainerWorkouts) {
            String gym = trainer.getLocations().isEmpty() ? "Невідомо" : trainer.getLocations().get(0);
            String name = null;
            result.add(new TrainerWorkoutDTO(workout.getId(), workout.getDate(), workout.isCompleted(), name, gym));
        }

        // Тренування клієнтів тренера
        List<User> clients = trainer.getClients();
        for (User client : clients) {
            List<Workout> clientWorkouts = workoutRepository.findByUserId(client.getId());
            for (Workout workout : clientWorkouts) {
                String gym = client.getLocations().isEmpty() ? "Невідомо" : client.getLocations().get(0);
                String name = client.getFirstName() + " " + client.getLastName();
                result.add(new TrainerWorkoutDTO(workout.getId(), workout.getDate(), workout.isCompleted(), name, gym));
            }
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/create")
    public Workout createWorkout(@RequestBody Workout workout) {
        // Усі вправи мають мати посилання назад на тренування
        if (workout.getExercises() != null) {
            for (Exercise e : workout.getExercises()) {
                e.setWorkout(workout);
            }
        }
        return workoutRepository.save(workout);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {
//        Optional<Workout> workoutOptional = workoutRepository.findById(id);
//        if (workoutOptional.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Workout workout = workoutOptional.get();
//        workout.setCompleted(updatedWorkout.isCompleted());
//        //workout.setDate(updatedWorkout.getDate());
//        // оновити інші поля за потребою
//
//        return ResponseEntity.ok(workoutRepository.save(workout));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> updateWorkout(@PathVariable Long id, @RequestBody Workout updatedWorkout) {
        Optional<Workout> workoutOptional = workoutRepository.findById(id);
        if (workoutOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Workout workout = workoutOptional.get();

        workout.setCompleted(updatedWorkout.isCompleted());

        if (updatedWorkout.getDate() != null) {
            workout.setDate(updatedWorkout.getDate());
        }

        if (updatedWorkout.getTitle() != null) {
            workout.setTitle(updatedWorkout.getTitle());
        }

        if (updatedWorkout.getNotation() != null) {
            workout.setNotation(updatedWorkout.getNotation());
        }

        if (updatedWorkout.getUser() != null) {
            workout.setUser(updatedWorkout.getUser());
        }

        if (updatedWorkout.getExercises() != null) {
            workout.getExercises().clear();
            updatedWorkout.getExercises().forEach(e -> {
                e.setWorkout(workout);
                workout.getExercises().add(e);
            });
        }

        return ResponseEntity.ok(workoutRepository.save(workout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long id) {
        if (!workoutRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        workoutRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

