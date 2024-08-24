/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Makersharks.service;


import com.example.Makersharks.model.ManufacturingProcess;
import com.example.Makersharks.model.NatureOfBusiness;
import com.example.Makersharks.repository.SupplierRepository;
import com.example.makersharks.model.Supplier;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> searchSuppliers(String location, NatureOfBusiness natureOfBusiness, ManufacturingProcess manufacturingProcess) {
        
        
        return supplierRepository.findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(location, natureOfBusiness, manufacturingProcess);
    }
    // New method to save a supplier
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }
    
    // New method for retrieving all suppliers
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }
}