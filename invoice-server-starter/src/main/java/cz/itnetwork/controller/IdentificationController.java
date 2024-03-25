package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.service.IdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/identification")
public class IdentificationController {
    @Autowired
    IdentificationService identificationService;

    /**
     * Retrieves sales invoices associated with a given identification number.
     * @param identificationNumber The identification number of the person
     * @return List of sales invoices
     */
    @GetMapping("/{identificationNumber}/sales")
    public List<InvoiceDTO> getSales(@PathVariable String identificationNumber) {
        return identificationService.getSales(identificationNumber);
    }

    /**
     * Retrieves a list of purchase invoices for a person with the specified identification number.
     * @param identificationNumber The identification number of the person
     * @return List of purchase invoices associated with the person
     */
    @GetMapping("/{identificationNumber}/purchases")
    public List<InvoiceDTO> getPurchases(@PathVariable String identificationNumber) {
        return identificationService.getPurchases(identificationNumber);
    }
}
