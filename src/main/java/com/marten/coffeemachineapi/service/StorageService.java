package com.marten.coffeemachineapi.service;

import com.marten.coffeemachineapi.model.CoffeeMachine;
import com.marten.coffeemachineapi.model.Storage;
import com.marten.coffeemachineapi.repository.StorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class StorageService {
    private final StorageRepository storageRepository;

    public Storage create(CoffeeMachine coffeeMachine, String selectedCoffee) {
        Assert.notNull(coffeeMachine, "coffeeMachine must not be null");
        String desc = "Вами было выбрано и приготовлено " + selectedCoffee;
        Storage storage = new Storage(coffeeMachine.getProducer(), desc, LocalDate.now());
        return storageRepository.save(storage);
    }
}
