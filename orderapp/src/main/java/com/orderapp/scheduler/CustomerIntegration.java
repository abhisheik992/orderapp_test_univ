package com.orderapp.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderapp.models.Customer;
import com.orderapp.payloads.ResponsePayload;
import com.orderapp.util.CustomerOrderConstants;

@Component
public class CustomerIntegration {

	private static Logger log = LogManager.getLogger(CustomerIntegration.class);

	@Autowired
	private RestTemplate restTemplate = new RestTemplate();

	@Scheduled(cron = "5 * * * * *") // period of time and point of time
	public ResponseEntity<?> run() {
		var headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		List<Customer> customersList = new ArrayList<Customer>();
		var response = this.restTemplate.getForEntity("http://localhost:8080"+CustomerOrderConstants.GET_ALL_ACIVE_CUSTOMERS, ResponsePayload.class);
		if(!ObjectUtils.isEmpty(response.getBody()) && response.getStatusCode()==HttpStatusCode.valueOf(200)) {
			var mapper = new ObjectMapper();
			customersList = mapper.convertValue(response.getBody().getData(),  new TypeReference<List<Customer>>() {});
			customersList = customersList.stream()
					.map(cust-> {
						cust.setFullName(cust.getFirstName()+" "+ cust.getSurName());
						return new Customer(cust.getId(), cust.getFirstName(), cust.getSurName(), cust.getFullName(), cust.getEmail(),cust.isStatus(), cust.getAddress(), cust.getOrdersList());
					})
					.collect(Collectors.toList());

		}
		if(customersList.size()>0) {
			var postResponse = this.restTemplate.postForEntity(CustomerOrderConstants.POST_TRANSFORM_DATA_URL, customersList, Object.class);
			log.info("-----------Status: {}-------------",postResponse.getStatusCode());
			return postResponse;
		}
		
		return null;
	}

}
