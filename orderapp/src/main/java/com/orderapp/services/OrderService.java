package com.orderapp.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.orderapp.interfaces.IOrder;
import com.orderapp.models.Order;
import com.orderapp.repositories.CustomerRepository;
import com.orderapp.repositories.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService implements IOrder {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Transactional
	@Override
	public Object saveOrdersFromCsv(MultipartFile orderFile) throws IOException, CsvException {
        var reader = new BufferedReader(new InputStreamReader(orderFile.getInputStream()));
        var csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build(); // skip the header row
        var rows = csvReader.readAll();
        var orders = new ArrayList<Order>();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (String[] row : rows) {
            var order = new Order();
            order.setId(Long.parseLong(row[0]));
            if(row[1]!=null) {
            	order.setOrderDate(LocalDate.parse(row[1], formatter));
            }
            if(row[3]!=null) {
                order.setAmount(Double.valueOf(row[3]));	
            }
            if(row[2]!=null) {
            	var customer = this.customerRepository.findById(Long.valueOf(row[2])).get();
            	if(!ObjectUtils.isEmpty(customer)) {
                	order.setCustomer(customer);  
                	var savedCustomer = this.orderRepository.save(order);
                	if(!ObjectUtils.isEmpty(savedCustomer)) {
                    	orders.add(order);
                    }
            	}
            }
        }
		return orders;
	}

}
