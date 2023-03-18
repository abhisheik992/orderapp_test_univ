package com.orderapp.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvException;
import com.orderapp.models.Customer;
import com.orderapp.payloads.IdRequestPayload;

public interface ICustomer {

	List<Customer> saveCustomersFromCsv(MultipartFile customerFile) throws IOException, CsvException;

	Customer getCustomerById(IdRequestPayload request);

	Object getAllCustomers();

}
