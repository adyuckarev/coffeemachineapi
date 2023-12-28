package com.marten.coffeemachineapi.service;

import com.marten.coffeemachineapi.error.NotFoundException;
import com.marten.coffeemachineapi.model.CoffeeMachine;
import com.marten.coffeemachineapi.repository.CoffeeMachineRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static com.marten.coffeemachineapi.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class CoffeeMachineService {
    private final CoffeeMachineRepository coffeeMachineRepository;

    public CoffeeMachine create(CoffeeMachine coffeeMachine) {
        Assert.notNull(coffeeMachine, "coffeeMachine must not be null");
        return coffeeMachineRepository.save(coffeeMachine);
    }

    public void update(CoffeeMachine coffeeMachine) {
        Assert.notNull(coffeeMachine, "coffeeMachine must not be null");
        checkNotFoundWithId(coffeeMachineRepository.save(coffeeMachine), coffeeMachine.id());
    }

    public CoffeeMachine get(int id) {
        return coffeeMachineRepository.get(id).orElseThrow(() -> new NotFoundException("coffeeMachine with " + id + " not found"));
    }

    public void delete(int id) {
        checkNotFoundWithId(coffeeMachineRepository.delete(id) != 0, id);
    }

    public void turnOn(int id) {
        changeStatus(id, true);
    }

    public void turnOff(int id) {
        changeStatus(id, false);
    }

    public void makeСoffee(int id, String selectedCoffee) {
        CoffeeMachine coffeeMachine = coffeeMachineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("coffeeMachine not found"));
        checkStatus(coffeeMachine);
        int volumeToSubtract = switch (selectedCoffee) {
            case "Капучино" -> 200;
            case "Латте" -> 100;
            case "Раф" -> 150;
            default -> throw new IllegalArgumentException("Invalid coffee type");
        };
        int newMaxVolume = coffeeMachine.getMaxVolume() - volumeToSubtract;
        if (newMaxVolume < 0) {
            throw new IllegalArgumentException("Not enough liquid");
        }
        coffeeMachine.setMaxVolume(newMaxVolume);
        coffeeMachineRepository.save(coffeeMachine);
    }

    private void checkStatus(CoffeeMachine coffeeMachine) {
        if (!coffeeMachine.isActive()) {
            throw new IllegalArgumentException("coffeeMachine not active");
        }
    }

    private void changeStatus(int id, boolean status) {
        CoffeeMachine coffeeMachine = coffeeMachineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("coffeeMachine not found"));

        coffeeMachine.setActive(status);
        coffeeMachineRepository.save(coffeeMachine);
    }
}
