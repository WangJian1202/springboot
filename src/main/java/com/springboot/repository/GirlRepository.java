package com.springboot.repository;

import com.springboot.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GirlRepository extends JpaRepository<Girl,Integer> {

}
