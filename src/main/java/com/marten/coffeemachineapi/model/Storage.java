package com.marten.coffeemachineapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "coffee_machines_storage")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Storage extends BaseEntity implements Serializable {
    private String coffeeMachineName;
    private String description;
    private LocalDate date;

    public Storage(Integer id, String coffeeMachineName, String description, LocalDate date) {
        super(id);
        this.coffeeMachineName = coffeeMachineName;
        this.description = description;
        this.date = date;
    }

    public Storage(String coffeeMachineName, String description, LocalDate date) {
        this.coffeeMachineName = coffeeMachineName;
        this.description = description;
        this.date = date;
    }
}
