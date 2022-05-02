package com.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer>{

}
