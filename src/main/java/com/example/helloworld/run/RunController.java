package com.example.helloworld.run;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private static final Logger log = LoggerFactory.getLogger(RunController.class);

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
        log.info("RunController has been created");
    }

    @GetMapping("")
    List<Run> findAll(){

        List<Run> runs = runRepository.findAll();
        log.info("Runs : {}", runs);
        return runs;
    }

    @GetMapping("/{id}")
    Run findOne(@PathVariable int id){

        Optional<Run> run = runRepository.findById(id);

        if(run.isEmpty()){
            throw new RunNotFoundException();
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run){
       runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run, @PathVariable int id){
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id){
        runRepository.deleteById(id);
    }

    @GetMapping("/location/{location}")
    List<Run> findAll(@PathVariable String location){
        return runRepository.findAllByLocation(location);
    }

}
