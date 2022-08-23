package com.tararantula.user_interface.repository;

import com.tararantula.user_interface.entity.Cheque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChequeRepository extends JpaRepository<Cheque,Integer> {
}
