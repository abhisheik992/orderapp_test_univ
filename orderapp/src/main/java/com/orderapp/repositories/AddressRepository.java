package com.orderapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orderapp.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
