package com.orderapp.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.orderapp.interfaces.ICustomer;
import com.orderapp.models.Address;
import com.orderapp.models.Customer;
import com.orderapp.payloads.IdRequestPayload;
import com.orderapp.repositories.AddressRepository;
import com.orderapp.repositories.CustomerRepository;

@Service
public class CustomerService implements ICustomer {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Customer> saveCustomersFromCsv(MultipartFile customerFile) throws IOException, CsvException {
		var reader = new BufferedReader(new InputStreamReader(customerFile.getInputStream()));
		var csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // skip the header row
		var rows = csvReader.readAll();
		var customers = new ArrayList<Customer>();
		for (String[] row : rows) {
			var customer = new Customer();
			customer.setId(Long.parseLong(row[0]));
			customer.setFirstName(row[1]);
			customer.setSurName(row[2]);
			customer.setEmail(row[3]);
			var address = new Address();
			address.setAddress(row[4]);
			address.setZipCode(row[5]);
			address.setRegion(row[6]);
			address = this.addressRepository.save(address);
			if (!ObjectUtils.isEmpty(address)) {
				customer.setAddress(address);
			}
			if (row[7] != null && row[7].equalsIgnoreCase("active")) {
				customer.setStatus(true);
			}
			if (row[7] != null && row[7].equalsIgnoreCase("archived")) {
				customer.setStatus(false);
			}

			customer = this.customerRepository.save(customer);
			if (!ObjectUtils.isEmpty(customer)) {
				customers.add(customer);
			}
		}
		return customers;
	}

	@Override
	public Customer getCustomerById(IdRequestPayload request) {
		return this.customerRepository.findById(request.getId()).get();
	}

	@Override
	public Object getAllCustomers() {
		//GET RESULT AS ENTITY CUSTOMER OBJECT WITH THE WHERE CLAUSE AND ORDERBY
		var list = this.customerRepository.findAllByStatusTrueOrderByIdAscAndOrdersOrderDateDesc(); 
		//GET RESULT AS CUSTOM CREATED OBJECT WITH THE WHERE CLAUSE AND ORDERBY
//		var list = this.customerRepository.getAllByStatusTrueAndOrderByIdAscAndOrderDateDesc(); 
		
		return list;
	}

}
