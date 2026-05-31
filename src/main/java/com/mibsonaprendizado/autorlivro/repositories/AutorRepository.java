package com.mibsonaprendizado.autorlivro.repositories;

import com.mibsonaprendizado.autorlivro.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

}
