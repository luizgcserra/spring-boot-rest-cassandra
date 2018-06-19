package com.example.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.contracts.Beneficiary;
import com.example.repository.BeneficiaryRepository;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

	@Autowired
	private final BeneficiaryRepository beneficiaryRepository;

	public BeneficiaryController(BeneficiaryRepository beneficiaryRepository) {
		this.beneficiaryRepository = beneficiaryRepository;
	}

	@GetMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<?> getOne(@PathVariable(value = "id") String id) {
		Beneficiary beneficiary = this.beneficiaryRepository.findOne(id);

		if (beneficiary != null) {
			return ResponseEntity.ok(beneficiary);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PostMapping(headers = "Accept=application/json")
	public ResponseEntity<?> add(@RequestBody Beneficiary input) {
		Beneficiary result = this.beneficiaryRepository.save(input);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getNisBeneficiario())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(headers = "Accept=application/json")
	public ResponseEntity<?> modify(@RequestBody Beneficiary input) {
		if (input != null && input.getNisBeneficiario().length() > 0) {
			this.beneficiaryRepository.save(input);

			return ResponseEntity.ok(input);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
		if (id != null && id.length() > 0) {
			this.beneficiaryRepository.delete(id);

			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<?> get(@RequestParam(value = "email", required = false) String name) {
		if (name != null) {
			return ResponseEntity.ok(this.beneficiaryRepository.findByName(name));
		} else {
			return ResponseEntity.ok(this.beneficiaryRepository.findAll());
		}
	}

}