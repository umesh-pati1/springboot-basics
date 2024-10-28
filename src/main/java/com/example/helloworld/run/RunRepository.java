package com.example.helloworld.run;

import com.example.helloworld.HelloworldApplication;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {
    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("SELECT * FROM Run").query(Run.class).list();
    }

    public Optional<Run> findById(int id){
        return jdbcClient.sql("SELECT * FROM Run WHERE id = :id").param("id", id).query(Run.class).optional();
    }

    public void create(Run run){
        var updated = jdbcClient.sql("INSERT INTO Run (id, title, started_on, completed_on, miles, location) VALUES (?, ?, ?, ?, ?, ?)").params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString())).update();
        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    public void update(Run run, int id){
        var updated = jdbcClient.sql("UPDATE Run SET title = ?, started_on = ?, completed_on = ? , miles = ? , location = ? where id = ?").params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), run.id())).update();
        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(int id){
         jdbcClient.sql("DELETE FROM Run WHERE id = :id").param("id", id).update();
    }

    public int count(){
        return jdbcClient.sql("SELECT * FROM Run").query().listOfRows().size();
    }

    public void saveAl(List<Run> runs){
        runs.forEach(this::create);
    }

    public List<Run> findByLocation(String location){
        return jdbcClient.sql("SELECT * FROM Run WHERE location = :location").param("location", location).query(Run.class).list();
    }

}
