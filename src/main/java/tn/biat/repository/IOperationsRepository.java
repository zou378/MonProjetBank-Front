package tn.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.biat.domain.Operation;


public interface IOperationsRepository extends JpaRepository<Operation, Long> {
	
}
