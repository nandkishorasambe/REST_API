
package com.example.Makersharks.repository;





import com.example.Makersharks.model.ManufacturingProcess;
import com.example.Makersharks.model.NatureOfBusiness;
import com.example.makersharks.model.Supplier;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author NandKishor
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{
 
    List<Supplier> findByLocationAndNatureOfBusinessAndManufacturingProcessesContaining(
            String location,
            NatureOfBusiness natureOfBusiness,
            ManufacturingProcess manufacturingProcess
    );
    
}
