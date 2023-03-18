package com.orderapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.orderapp.models.Customer;
import com.orderapp.payloads.CustomerOrder;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	@Query(value = "SELECT new com.orderapp.payloads.CustomerOrder(c.id as customerId, c.email as email, c.firstName as firstname, c.surName as surname, "
			+ "o.id as orderid, o.amount as amount, o.orderDate as order_date) FROM Customer c "
			+ "LEFT JOIN Order o "
			+ "ON c.id=o.customer.id "
			+ "WHERE c.status=True "
			+ "ORDER BY c.id ASC,  o.orderDate DESC")
	List<CustomerOrder> getAllByStatusTrueAndOrderByIdAscAndOrderDateDesc();
	
	
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.ordersList o WHERE c.status = true ORDER BY c.id ASC, o.orderDate DESC")
    List<Customer> findAllByStatusTrueOrderByIdAscAndOrdersOrderDateDesc();

}
