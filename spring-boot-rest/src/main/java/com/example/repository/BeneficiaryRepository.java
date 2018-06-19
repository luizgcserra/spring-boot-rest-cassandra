package com.example.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.contracts.Beneficiary;

public interface BeneficiaryRepository extends CrudRepository<Beneficiary, String> {

	@Query(value="SELECT * FROM beneficiary WHERE nomeBeneficiario=?0")
	public List<Beneficiary> findByName(String name);
}
