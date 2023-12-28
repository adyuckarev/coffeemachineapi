package com.marten.coffeemachineapi.repository;

import com.marten.coffeemachineapi.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
}
