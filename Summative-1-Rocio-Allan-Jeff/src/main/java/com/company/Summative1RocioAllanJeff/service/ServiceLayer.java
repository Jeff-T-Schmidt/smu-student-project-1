package com.company.Summative1RocioAllanJeff.service;

import com.company.Summative1RocioAllanJeff.controller.InvoiceController;
import com.company.Summative1RocioAllanJeff.model.*;
import com.company.Summative1RocioAllanJeff.repository.*;
import com.company.Summative1RocioAllanJeff.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {


    private InvoiceRepository invoiceRepo;
    private TaxRatesRepository taxRepo;
    private ProcessingFeesRepository feesRepo;
    private ConsoleRepository consoleRepository;
    private GameRepository gameRepository;
    private TshirtRepository tshirtRepository;

    @Autowired
    public ServiceLayer(InvoiceRepository invoiceRepo, TaxRatesRepository taxRepo, ProcessingFeesRepository feesRepo, ConsoleRepository consoleRepository, GameRepository gameRepository, TshirtRepository tshirtRepository) {
        this.invoiceRepo = invoiceRepo;
        this.taxRepo = taxRepo;
        this.feesRepo  = feesRepo;
        this.consoleRepository = consoleRepository;
        this.gameRepository = gameRepository;
        this.tshirtRepository = tshirtRepository;
    }


    //generate invoice method
//    public Invoice generateInvoice(Invoice inputInvoice) {
    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel viewModel) {
        //create the new business logic here
        //check the viewmodel item type
        //if it is a game go to the game repo and get the game

        if (viewModel.getItemType().equals("Game")) {
            //returns the entire game
            // "Optional" is like the amazon box - hoping there is a game in the box
            Optional<Game> returnGame = gameRepository.findById(viewModel.getItemId());
            //get information out of the game you need
            // isPresent = look into the box and get that game
            if (returnGame.isPresent()) {
                Game actualGame = returnGame.get();
                //check the inventory quantity
                //Validate inputInvoice.getQuantity >= 0
                // if inventoryQuantity < inputInvoice getQuantity
                //if you want the value of a colum you need to use the getter
                // check db for available quantity
                if (actualGame.getQuantity() >= viewModel.getQuantity()) {
                    //Item inventory - order quantity
                    //update the db
                    // calc the quantity
                    //put that in the game
                    //save that info into the db
                    int updatedGameQuantity = actualGame.getQuantity() - viewModel.getQuantity();
                    //setting the instance variable
                    //setting the order quantity
                    actualGame.setQuantity(updatedGameQuantity);
                    gameRepository.save(actualGame);
                    //for itemId, look for itemName, .setUnitPrice
                    float unitPrice = actualGame.getPrice();
                    //setting the order unitPrice
                    viewModel.setUnitPrice(unitPrice);
                }
            }
        } else if (viewModel.getItemType().equals("Console")) {
            Optional<Console> returnConsole = consoleRepository.findById(viewModel.getItemId());
            if (returnConsole.isPresent()) {
                Console actualConsole = returnConsole.get();
                if (actualConsole.getQuantity() >= viewModel.getQuantity()) {
                    int updatedConsoleQuantity = actualConsole.getQuantity() - viewModel.getQuantity();
                    actualConsole.setQuantity(updatedConsoleQuantity);
                    consoleRepository.save(actualConsole);
                    float unitPrice = actualConsole.getPrice();
                    viewModel.setUnitPrice(unitPrice);
                }
            }
        } else if (viewModel.getItemType().equals("Tshirt")) {
            Optional<Tshirt> returnTshirt = tshirtRepository.findById(viewModel.getItemId());
            if (returnTshirt.isPresent()) {
                Tshirt actualTshirt = returnTshirt.get();
                if (actualTshirt.getQuantity() >= viewModel.getQuantity()) {
                    int updatedTshirtQuantity = actualTshirt.getQuantity() - viewModel.getQuantity();
                    actualTshirt.setQuantity(updatedTshirtQuantity);
                    tshirtRepository.save(actualTshirt);
                    float unitPrice = actualTshirt.getPrice();
                    viewModel.setUnitPrice(unitPrice);
                }
            }
            //return exception
        } else {
            //this can be custom error handling/response
            throw new RuntimeException("Invalid item type!" + viewModel.getItemType());
        }
        double subtotal = findSubtotal(viewModel.getUnitPrice(), viewModel.getQuantity());
        viewModel.setSubtotal(subtotal);
        double calculatedTax = findTax(viewModel.getState(), viewModel.getSubtotal());
        viewModel.setTax(calculatedTax);
        double calculatedProcessingFee = findProcessingFee(viewModel.getQuantity(), viewModel.getItemType());
        viewModel.setProcessingFee(calculatedProcessingFee);
        double total = findTotal(subtotal, calculatedTax, calculatedProcessingFee);
        viewModel.setTotal(total);

        //create new constructor based on Invoice model

        Invoice returnInvoice = new Invoice();
        //set name, street, city, state
        returnInvoice.setName(viewModel.getName());
        returnInvoice.setStreet(viewModel.getStreet());
        returnInvoice.setCity(viewModel.getCity());
        returnInvoice.setState(viewModel.getState());
        returnInvoice.setZipCode(viewModel.getZipCode());
        returnInvoice.setItemType(viewModel.getItemType());
        returnInvoice.setItemId(viewModel.getItemId());
        returnInvoice.setQuantity(viewModel.getQuantity());
        returnInvoice.setUnitPrice(viewModel.getUnitPrice());
        returnInvoice.setSubtotal(viewModel.getSubtotal());
        returnInvoice.setTax(viewModel.getTax());
        returnInvoice.setProcessingFee(viewModel.getProcessingFee());
        returnInvoice.setTotal(viewModel.getTotal());

//        returnInvoice.setInvoiceId(viewModel.getId());

        returnInvoice = invoiceRepo.save(returnInvoice);
        viewModel.setId(returnInvoice.getInvoiceId());

        return viewModel;
    }

    public double findSubtotal(double unitPrice, double quantity) {

        return unitPrice * quantity;

    }

    public double findTax(String state, double subtotal) {
        //use repo to look up tax rate
        //taxRepo.findById(state);
        //set a variable to taxRate.getRate()
        Optional<TaxRate> taxRateOptional = taxRepo.findById(state);
        //return that variable * subtotal
        if (taxRateOptional.isPresent()) {
            TaxRate actualTaxRate = taxRateOptional.get();
            float rate = actualTaxRate.getRate();
            return rate * subtotal;
        }

        throw new RuntimeException("No tax rate for provided state!");
    }

    public double findProcessingFee(int quantity, String productType) {
        // user repo to look up processing fee
        Optional<ProcessingFee> processingFeeByType = feesRepo.findById(productType);
        if (processingFeeByType.isPresent()) {
            ProcessingFee actualProcessingFee = processingFeeByType.get();
            float fee = actualProcessingFee.getFee();
            double otherFee = 15.49;
            // if quantity is <= 10, return fee
            if (quantity <= 10) {
                return fee;
            } else {
                return fee + otherFee;
            }
            // else return fee + otherFee;
        } else {
            throw new RuntimeException("No fees found for product type!");
        }
    }

    
    public double findTotal(double subtotal, double tax, double processingFee){
        return subtotal + tax + processingFee;
    }

    //cost of items = invoiceInput.subtotal
    // return invoice.subtotal * (state tax rate)
    //if inputInvoice.getQuantity >=10
    //setProcessingFee to $15.49 + (tshirt/console/game) fee
    //else setProcessingFee to (tshirt/console/game) fee
    //return invoiceInput.subtotal * inputInvoice.getState(stateTax) + (result from processing fee)


    //"Clean" - create a different method for each business rule


    // referenced record-collection activity in class on Oct. 14, 2022
    //build InvoiceViewModel to create a list of invoices
    private InvoiceViewModel buildInvoiceViewModel (Invoice invoice) {

        InvoiceViewModel returnVal = new InvoiceViewModel();
        returnVal.setId(invoice.getInvoiceId());
        returnVal.setName(invoice.getName());
        returnVal.setStreet(invoice.getStreet());
        returnVal.setCity(invoice.getCity());
        returnVal.setState(invoice.getState());
        returnVal.setZipCode(invoice.getZipCode());
        returnVal.setItemType(invoice.getItemType());
        returnVal.setItemId(invoice.getItemId());
        returnVal.setQuantity(invoice.getQuantity());
        returnVal.setUnitPrice(invoice.getUnitPrice());
        returnVal.setSubtotal(invoice.getSubtotal());
        returnVal.setTax(invoice.getTax());
        returnVal.setProcessingFee(invoice.getProcessingFee());
        returnVal.setTotal(invoice.getTotal());

        return returnVal;
    }
    // find all invoices
    public List<InvoiceViewModel> findAllInvoices() {

        List<Invoice> invoiceList = invoiceRepo.findAll();

        List<InvoiceViewModel> returnValList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel avm = buildInvoiceViewModel(invoice);
            returnValList.add(avm);
        }

        return returnValList;
    }
    // find an invoice by id
    public InvoiceViewModel findInvoiceById(int id) {

        // Get the invoice object first
        Optional<Invoice> invoice = invoiceRepo.findById(id);

        return invoice.isPresent() ? buildInvoiceViewModel(invoice.get()) : null;
    }
    // update an invoice
    @Transactional
    public void updateInvoice(InvoiceViewModel invoiceViewModel) {

        // Update the album information
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceViewModel.getId());
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipCode(invoiceViewModel.getZipCode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setQuantity(invoiceViewModel.getQuantity());
        invoice.setUnitPrice(invoiceViewModel.getUnitPrice());
        invoice.setSubtotal(invoiceViewModel.getSubtotal());
        invoice.setTax(invoiceViewModel.getTax());
        invoice.setProcessingFee(invoiceViewModel.getProcessingFee());
        invoice.setTotal(invoiceViewModel.getTotal());

        invoiceRepo.save(invoice);

    }
    //delete an invoice
    @Transactional
    public void deleteInvoiceById(int id) {

        // Remove album
        invoiceRepo.deleteById(id);

    }
}



