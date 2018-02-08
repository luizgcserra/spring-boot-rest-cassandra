package com.example.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.contracts.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	@Query(value="SELECT * FROM customer WHERE email=?0")
	public List<Customer> findByEmail(String email);
}
