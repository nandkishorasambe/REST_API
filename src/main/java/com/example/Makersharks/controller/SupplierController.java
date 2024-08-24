package com.example.Makersharks.controller;

import com.example.Makersharks.model.ManufacturingProcess;
import com.example.Makersharks.model.NatureOfBusiness;
import com.example.makersharks.model.Supplier; // Correct import statement
import com.example.Makersharks.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @PostMapping("/query")
    public ResponseEntity<List<Supplier>> searchSuppliers(@RequestBody SupplierSearchRequest searchRequest) {
        try {
            // Use the search parameters from the request object
            List<Supplier> suppliers = supplierService.searchSuppliers(
                searchRequest.getLocation(), 
                searchRequest.getNatureOfBusiness(), 
                searchRequest.getManufacturingProcess()
            );

            if (suppliers.isEmpty()) {
                // Log no content found
                System.out.println("No suppliers found.");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // Log content found
            System.out.println("Suppliers found: " + suppliers.size());
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Handle invalid enum values with a 400 Bad Request response
            System.out.println("Invalid parameters provided.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Handle any other exceptions with a 500 Internal Server Error response
            System.out.println("Internal server error.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // New method to add a supplier
    @PostMapping("/add")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        try {
            Supplier savedSupplier = supplierService.addSupplier(supplier);
            return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // New GET method to retrieve all suppliers
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierService.getAllSuppliers();
            if (suppliers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


// Create a new class for the search request payload
class SupplierSearchRequest {
    private String location;
    private NatureOfBusiness natureOfBusiness;
    private ManufacturingProcess manufacturingProcess;

    // Getters and setters

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public NatureOfBusiness getNatureOfBusiness() {
        return natureOfBusiness;
    }

    public void setNatureOfBusiness(NatureOfBusiness natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }

    public ManufacturingProcess getManufacturingProcess() {
        return manufacturingProcess;
    }

    public void setManufacturingProcess(ManufacturingProcess manufacturingProcess) {
        this.manufacturingProcess = manufacturingProcess;
    }
}
