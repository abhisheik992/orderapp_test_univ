package com.orderapp.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.orderapp.OrderappApplication;
import com.orderapp.exceptions.EntityNotFoundException;
import com.orderapp.exceptions.ErrorResponse;
import com.orderapp.exceptions.NullPointerCustomException;
import com.orderapp.interfaces.ICustomer;
import com.orderapp.interfaces.IOrder;
import com.orderapp.payloads.IdRequestPayload;
import com.orderapp.payloads.ResponsePayload;
import com.orderapp.scheduler.CustomerIntegration;
import com.orderapp.util.CustomerOrderConstants;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private static Logger log = LogManager.getLogger(OrderappApplication.class);
	
	@Autowired
	private ICustomer customerService;
	
	@Autowired
	private IOrder orderService;
	
	@PostMapping("/importcustomers")
	private ResponseEntity<ResponsePayload> addCustomers(@RequestParam("customerfile") MultipartFile customerFile) {

		ResponsePayload response = new ResponsePayload();
		try {
			log.info("------------------- starting addCustomers method --------------");

			var customerList = this.customerService.saveCustomersFromCsv(customerFile);
			
			if (ObjectUtils.isEmpty(customerList)) {
				throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
						CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
			}
			if (!ObjectUtils.isEmpty(customerList)) {
				response.setStatus("200");
				response.setMessage(CustomerOrderConstants.SUCCESS);
				response.setData(customerList);
			}

			log.info("------------------- response -------------- " + response.toString());
			log.info("------------------- ending addCustomers method --------------");

		} catch (EntityNotFoundException e) {
			log.error("----------------- EntityNotFoundException in addCustomers method --------------");
			throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (NullPointerException e) {
			log.error("----------------- NullPointerException in addCustomers method --------------");
			throw new NullPointerCustomException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			log.error("----------------- Exception in addCustomers method --------------");
			e.printStackTrace();
		}

		return new ResponseEntity<ResponsePayload>(response, HttpStatus.OK);
	}
	
	@PostMapping("/importorders")
	private ResponseEntity<ResponsePayload> addOrders(@RequestParam("orderfile") MultipartFile orderFile) {

		ResponsePayload response = new ResponsePayload();
		try {
			log.info("------------------- starting addOrders method --------------");

			var orderList = this.orderService.saveOrdersFromCsv(orderFile);

			if (ObjectUtils.isEmpty(orderList)) {
				throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
						CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
			}
			if (!ObjectUtils.isEmpty(orderList)) {
				response.setStatus("200");
				response.setMessage(CustomerOrderConstants.SUCCESS);
				response.setData(orderList);
			}

			log.info("------------------- response -------------- " + response.toString());
			log.info("------------------- ending addOrders method --------------");

		} catch (EntityNotFoundException e) {
			log.error("----------------- EntityNotFoundException in addOrders method --------------");
			throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.ORDER_NOT_FOUND,
					CustomerOrderConstants.ORDER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (NullPointerException e) {
			log.error("----------------- NullPointerException in addOrders method --------------");
			throw new NullPointerCustomException(new ErrorResponse(CustomerOrderConstants.ORDER_NOT_FOUND,
					CustomerOrderConstants.ORDER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			log.error("----------------- Exception in addOrders method --------------");
			e.printStackTrace();
		}

		return new ResponseEntity<ResponsePayload>(response, HttpStatus.OK);
	}

	@PostMapping("/getcustomerbyid")
	private ResponseEntity<ResponsePayload> getCustomerById(@RequestBody IdRequestPayload request) {

		ResponsePayload response = new ResponsePayload();
		try {
			log.info("------------------- starting getCustomerById method --------------");

			var customer = this.customerService.getCustomerById(request);

			if (ObjectUtils.isEmpty(customer)) {
				throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
						CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
			}
			if (!ObjectUtils.isEmpty(customer)) {
				response.setStatus("200");
				response.setMessage(CustomerOrderConstants.SUCCESS);
				response.setData(customer);
			}
			log.info("------------------- response -------------- " + response.toString());
			log.info("------------------- ending getCustomerById method --------------");

		} catch (EntityNotFoundException e) {
			log.error("----------------- EntityNotFoundException in getCustomerById method --------------");
			throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (NullPointerException e) {
			log.error("----------------- NullPointerException in getCustomerById method --------------");
			throw new NullPointerCustomException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			log.error("----------------- Exception in getCustomerById method --------------");
			e.printStackTrace();
		}

		return new ResponseEntity<ResponsePayload>(response, HttpStatus.OK);
	}

	@GetMapping("/getallcustomers")
	private ResponseEntity<ResponsePayload> getAllCustomers() {

		ResponsePayload response = new ResponsePayload();
		try {
			log.info("------------------- starting getAllCustomers method --------------");

			var customer = this.customerService.getAllCustomers();

			if (ObjectUtils.isEmpty(customer)) {
				throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
						CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
			}
			if (!ObjectUtils.isEmpty(customer)) {
				response.setStatus("200");
				response.setMessage(CustomerOrderConstants.SUCCESS);
				response.setData(customer);
			}

			log.info("------------------- response -------------- " + response.toString());
			log.info("------------------- ending getAllCustomers method --------------");
			
		} catch (EntityNotFoundException e) {
			log.error("----------------- EntityNotFoundException in getAllCustomers method --------------");
			throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (NullPointerException e) {
			log.error("----------------- NullPointerException in getAllCustomers method --------------");
			throw new NullPointerCustomException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			log.error("----------------- Exception in getAllCustomers method --------------");
			e.printStackTrace();
		}

		return new ResponseEntity<ResponsePayload>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getTrandformData")
	private ResponseEntity<ResponsePayload> getAllTranformedData() {

		ResponsePayload response = new ResponsePayload();
		try {
			log.info("------------------- starting getAllCustomers method --------------");
			
			CustomerIntegration  integration = new CustomerIntegration();
			var customer = integration.run();

			if (ObjectUtils.isEmpty(customer)) {
				throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
						CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
			}
			if (!ObjectUtils.isEmpty(customer)) {
				response.setStatus("200");
				response.setMessage(CustomerOrderConstants.SUCCESS);
				response.setData(customer);
			}

			log.info("------------------- response -------------- " + response.toString());
			log.info("------------------- ending getAllTranformedData method --------------");
			
		} catch (EntityNotFoundException e) {
			log.error("----------------- EntityNotFoundException in getAllTranformedData method --------------");
			throw new EntityNotFoundException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (NullPointerException e) {
			log.error("----------------- NullPointerException in getAllTranformedData method --------------");
			e.printStackTrace();
			throw new NullPointerCustomException(new ErrorResponse(CustomerOrderConstants.CUSTOMER_NOT_FOUND,
					CustomerOrderConstants.CUSTOMER_NOT_PRESENT_WITH_ID, HttpStatus.NOT_FOUND));
		} catch (Exception e) {
			log.error("----------------- Exception in getAllTranformedData method --------------");
			e.printStackTrace();
		}

		return new ResponseEntity<ResponsePayload>(response, HttpStatus.OK);
	}
}
