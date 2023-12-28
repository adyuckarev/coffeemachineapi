package com.marten.coffeemachineapi.repository;

import com.marten.coffeemachineapi.model.CoffeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CoffeeMachineRepository extends JpaRepository<CoffeeMachine, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM CoffeeMachine c WHERE c.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT c FROM CoffeeMachine c WHERE c.id=:id")
    Optional<CoffeeMachine> get(@Param("id") int id);
}
