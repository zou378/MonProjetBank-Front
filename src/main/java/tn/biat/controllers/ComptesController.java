package tn.biat.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.biat.domain.Compte;
import tn.biat.repository.IComptesRespository;

@RestController
@CrossOrigin
public class ComptesController {
	// @Autowired

	private IComptesRespository repo;

	public ComptesController(IComptesRespository repo) { // @Autowired nest pas necessaire depuis v4.3
		this.repo = repo;
	}

	// @RequestMapping(path = "/comptes", method = RequestMethod.GET)
	// , produces=
	// {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
	@GetMapping(path="/comptes" )
	public List<Compte> getAllComptes() {
		return repo.findAll();
	}

	// public ResponseEntity<Compte> getCompteById(@PathVariable(value="id") String
	// myId)
	@GetMapping(path = "/comptes/{id}")
	public ResponseEntity<Compte> getCompteById(@PathVariable String id) {
		Optional<Compte> cpt = repo.findById(id);
		if (!cpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cpt.get());
	}

	@PostMapping(path = "/comptes")
	public ResponseEntity<Compte> addCompte(@RequestBody Compte c) {
		repo.save(c);
		return new ResponseEntity<Compte>(c, HttpStatus.CREATED);
	}

	@PutMapping(path = "/comptes")
	public ResponseEntity<Compte> updateCompte(@RequestBody Compte c) {
		repo.save(c);
		return new ResponseEntity<Compte>(c, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/comptes/{id}")
	public ResponseEntity<Compte> deleteCompte(@PathVariable(value = "id") String myId) {
		Optional<Compte> cpt = repo.findById(myId);
		if (!cpt.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {

			repo.delete(cpt.get());
			// repo.deleteById(myId);
			return new ResponseEntity<Compte>(HttpStatus.ACCEPTED);
		}

	}

}
