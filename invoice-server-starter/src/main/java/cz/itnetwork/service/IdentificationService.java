package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;

import java.util.List;

public interface IdentificationService {
    /**
     * Retrieves sales invoices for an individual identified by the provided identification number.
     * @param identificationNumber Identification number of the individual
     * @return List of sales invoices for the individual
     */
    List<InvoiceDTO> getSales(String identificationNumber);

    /**
     * Retrieves purchase invoices for an individual identified by the provided identification number.
     * @param identificationNumber Identification number of the individual
     * @return List of purchase invoices for the individual
     */
    List<InvoiceDTO> getPurchases(String identificationNumber);
}
