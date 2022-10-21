package com.company.Summative1RocioAllanJeff.controller;


import com.company.Summative1RocioAllanJeff.model.Game;
import com.company.Summative1RocioAllanJeff.model.Invoice;
import com.company.Summative1RocioAllanJeff.model.ProcessingFee;
import com.company.Summative1RocioAllanJeff.model.TaxRate;
import com.company.Summative1RocioAllanJeff.repository.InvoiceRepository;
import com.company.Summative1RocioAllanJeff.repository.ProcessingFeesRepository;
import com.company.Summative1RocioAllanJeff.repository.TaxRatesRepository;
import com.company.Summative1RocioAllanJeff.service.ServiceLayer;
import com.company.Summative1RocioAllanJeff.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {

    @Autowired
    private ServiceLayer serviceLayer;

    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        //creates Invoice via the service layer
        return serviceLayer.saveInvoice(invoiceViewModel);
    }

//
//    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<Invoice> getAllInvoices() {
//        return invoiceRepo.findAll();
//    }
//    @GetMapping("/invoices/{invoiceId}")
//    @ResponseStatus(HttpStatus.OK)
//    public Optional<Invoice> getInvoiceById(@PathVariable Integer invoiceId) {
//        return invoiceRepo.findById(invoiceId);
//    }

}
