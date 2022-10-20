package com.company.Summative1RocioAllanJeff.controller;


import com.company.Summative1RocioAllanJeff.model.Game;
import com.company.Summative1RocioAllanJeff.model.Invoice;
import com.company.Summative1RocioAllanJeff.model.ProcessingFee;
import com.company.Summative1RocioAllanJeff.model.TaxRate;
import com.company.Summative1RocioAllanJeff.repository.InvoiceRepository;
import com.company.Summative1RocioAllanJeff.repository.ProcessingFeesRepository;
import com.company.Summative1RocioAllanJeff.repository.TaxRatesRepository;
import com.company.Summative1RocioAllanJeff.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRepo;
    private TaxRatesRepository taxRepo;
    private ProcessingFeesRepository feesRepo;

//    private List<TaxRate> taxRateList = new ArrayList<>(Arrays.asList(
//            new TaxRate("AL", .05f),
//            new TaxRate("AK", .06f),
//            new TaxRate("AZ", .04f),
//            new TaxRate("AR", .06f),
//            new TaxRate("CA", .06f),
//            new TaxRate("CO", .04f),
//            new TaxRate("CT", .03f),
//            new TaxRate("DE", .05f),
//            new TaxRate("FL", .06f),
//            new TaxRate("GA", .07f),
//            new TaxRate("HI", .05f),
//            new TaxRate("ID", .05f),
//            new TaxRate("IL", .05f),
//            new TaxRate("IN", .05f),
//            new TaxRate("IA", .04f),
//            new TaxRate("KS", .06f),
//            new TaxRate("KY", .04f),
//            new TaxRate("LA", .05f),
//            new TaxRate("ME", .03f),
//            new TaxRate("MD", .07f),
//            new TaxRate("MA", .05f),
//            new TaxRate("MI", .06f),
//            new TaxRate("MN", .06f),
//            new TaxRate("MS", .05f),
//            new TaxRate("MO", .05f),
//            new TaxRate("MT", .03f),
//            new TaxRate("NE", .04f),
//            new TaxRate("NV", .04f),
//            new TaxRate("NH", .06f),
//            new TaxRate("NJ", .05f),
//            new TaxRate("NM", .05f),
//            new TaxRate("NY", .06f),
//            new TaxRate("NC", .05f),
//            new TaxRate("ND", .05f),
//            new TaxRate("OH", .04f),
//            new TaxRate("OK", .04f),
//            new TaxRate("OR", .07f),
//            new TaxRate("PA", .06f),
//            new TaxRate("RI", .06f),
//            new TaxRate("SC", .06f),
//            new TaxRate("SD", .06f),
//            new TaxRate("TN", .05f),
//            new TaxRate("TX", .03f),
//            new TaxRate("UT", .04f),
//            new TaxRate("VT", .07f),
//            new TaxRate("VA", .06f),
//            new TaxRate("WA", .05f),
//            new TaxRate("WV", .05f),
//            new TaxRate("WI", .03f),
//            new TaxRate("WY", .04f)
//
//
//    ));
    private List<ProcessingFee> processingFeeList = new ArrayList<>(Arrays.asList(
            new ProcessingFee("Consoles", 14.99f),
            new ProcessingFee("T-Shirts", 1.98f),
            new ProcessingFee("Games", 1.49f)
    ));


    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice invoice) {
        //creates Invoice via the service layer
        return serviceLayer.save(invoice);
    }
    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }
    @GetMapping("/invoices/{invoiceId}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Invoice> getInvoiceById(@PathVariable Integer invoiceId) {
        return invoiceRepo.findById(invoiceId);
    }
}
