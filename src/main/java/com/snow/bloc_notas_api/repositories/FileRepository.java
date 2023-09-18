package com.snow.bloc_notas_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.snow.bloc_notas_api.models.File;

/**
 * Connection to database with File model
 */
@Repository
public interface FileRepository extends JpaRepository<File, String> {
    
}