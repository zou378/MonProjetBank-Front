package tn.biat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.biat.domain.Compte;

public interface IComptesRespository extends JpaRepository<Compte, String> {

}
