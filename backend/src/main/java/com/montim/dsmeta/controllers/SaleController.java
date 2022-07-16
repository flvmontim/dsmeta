package com.montim.dsmeta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.montim.dsmeta.entities.Sale;
import com.montim.dsmeta.services.SaleService;
import com.montim.dsmeta.services.SmsService;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService saleService;
	@Autowired
	private SmsService smsService;

	@GetMapping
	public Page<Sale> getSales(@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate, Pageable page) {
		return saleService.getSales(minDate, maxDate, page);
	}

	@GetMapping("/notification/{id}")
	public void notifySms(@PathVariable Long id) {
		smsService.sendSms(id);
	}

	@GetMapping("/notification")
	public void notifySms() {
		smsService.sendSms();
	}

}
