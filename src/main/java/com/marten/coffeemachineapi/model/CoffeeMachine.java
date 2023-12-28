package com.marten.coffeemachineapi.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "coffee_machines")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoffeeMachine extends BaseEntity implements Serializable {
    private String producer;
    private int maxVolume;
    private boolean isActive;

    public CoffeeMachine(Integer id, String producer, int maxVolume, boolean isActive) {
        super(id);
        this.producer = producer;
        this.maxVolume = maxVolume;
        this.isActive = isActive;
    }

    public CoffeeMachine(String producer, int maxVolume, boolean isActive) {
        this.producer = producer;
        this.maxVolume = maxVolume;
        this.isActive = isActive;
    }
}
