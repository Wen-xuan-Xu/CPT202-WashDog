package com.cpt202.group7.repository;

import com.cpt202.group7.entity.Pet;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PetRepository extends JpaRepository<Pet, Integer> {
}
