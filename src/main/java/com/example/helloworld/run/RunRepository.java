package com.example.helloworld.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }

    Optional<Run> findById(int id){
        return runs.stream().filter((item) -> item.id() == id).findFirst();
    }

    void create(Run run){
        runs.add(run);
    }

    void update(Run run, int id){
        Optional<Run> existingRun = findById(id);
        existingRun.ifPresent(value -> runs.set(runs.indexOf(value), run));
    }

    void delete(int id){
        runs.removeIf((run)-> run.id() == id);
    }

    @PostConstruct
    private void init(){
        runs.add(
                new Run(1, "Spring", LocalDateTime.now(), LocalDateTime.now().plusHours(1),2 , Location.OUTDOOR)
        );

        runs.add(
                new Run(2, "Boot", LocalDateTime.now(), LocalDateTime.now().plusHours(1),2 , Location.OUTDOOR)
        );
    }





}
