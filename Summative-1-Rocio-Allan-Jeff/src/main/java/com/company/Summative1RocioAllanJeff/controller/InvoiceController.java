package com.company.Summative1RocioAllanJeff.controller;


import com.company.Summative1RocioAllanJeff.service.ServiceLayer;
import com.company.Summative1RocioAllanJeff.viewmodel.InvoiceViewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
// referenced record-collection activity in class on Oct. 14, 2022
@RestController
public class InvoiceController {

    @Autowired
    private ServiceLayer serviceLayer;

    // create an invoice
    @PostMapping("/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel addInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {
        //creates an Invoice via the service layer
        return serviceLayer.saveInvoice(invoiceViewModel);
    }
    // get all invoices
    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return serviceLayer.findAllInvoices();
    }
    // get an invoice by id
    @GetMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable int id) {
        return serviceLayer.findInvoiceById(id);
    }
    // update an invoice
    @PutMapping("/invoices")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel ) {
        serviceLayer.updateInvoice(invoiceViewModel);
    }
    // delete an invoice
    @DeleteMapping("/invoices/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        serviceLayer.deleteInvoiceById(id);
    }

}
