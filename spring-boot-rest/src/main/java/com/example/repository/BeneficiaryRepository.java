package com.example.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.model.Beneficiary;
import com.example.model.BeneficiaryKey;

public interface BeneficiaryRepository extends CrudRepository<Beneficiary, BeneficiaryKey> {

	@Query(value="SELECT * FROM beneficiary WHERE nomeBeneficiario=?0")
	public List<Beneficiary> findByName(String name);
	
	@Query(value="SELECT * FROM beneficiary WHERE nisBeneficiario=?0")
	public List<Beneficiary> findByNis(String nisBeneficiario);
}
