package com.marten.coffeemachineapi.controller;

import com.marten.coffeemachineapi.model.CoffeeMachine;
import com.marten.coffeemachineapi.service.CoffeeMachineService;
import com.marten.coffeemachineapi.service.StorageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.marten.coffeemachineapi.util.ValidationUtil.assureIdConsistent;
import static com.marten.coffeemachineapi.util.ValidationUtil.checkNew;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = CoffeeMachineController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CoffeeMachineController {
    private final Logger log = getLogger(getClass());
    private final CoffeeMachineService coffeeMachineService;
    private final StorageService storageService;
    static final String REST_URL = "/api/coffeemachines";

    @GetMapping("/{id}")
    public CoffeeMachine get(@PathVariable int id) {
        return coffeeMachineService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        coffeeMachineService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CoffeeMachine> createWithLocation(@Valid @RequestBody CoffeeMachine coffeeMachine) {
        log.info("create {}", coffeeMachine);
        checkNew(coffeeMachine);
        CoffeeMachine created = coffeeMachineService.create(coffeeMachine);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody CoffeeMachine coffeeMachine, @PathVariable int id) {
        log.info("update {} with id={}", coffeeMachine, id);
        assureIdConsistent(coffeeMachine, id);
        coffeeMachineService.update(coffeeMachine);
    }

    @PatchMapping("/{id}/turnOn")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void turnOn(@PathVariable int id) {
        coffeeMachineService.turnOn(id);
    }

    @PatchMapping("/{id}/turnOff")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void turnOff(@PathVariable int id) {
        coffeeMachineService.turnOff(id);
    }

    @PatchMapping("/{id}/makeСoffee")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void makeСoffee(@PathVariable int id, String selectedCoffee) {
        coffeeMachineService.makeСoffee(id, selectedCoffee);
        CoffeeMachine coffeeMachine = coffeeMachineService.get(id);
        storageService.create(coffeeMachine, selectedCoffee);
    }
}
