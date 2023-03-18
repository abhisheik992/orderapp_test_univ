package com.orderapp.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.exceptions.CsvException;

public interface IOrder {

	Object saveOrdersFromCsv(MultipartFile orderFile) throws IOException, CsvException;

}
