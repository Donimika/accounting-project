package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    /**
     * Creates a new invoice.
     * @param invoiceDTO The invoice data
     * @return The created invoice DTO
     */
    @PostMapping("")
    public InvoiceDTO createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoice(invoiceDTO);
    }

    /**
     * Retrieves all invoices based on the provided filter criteria.
     * @param invoiceFilter The filter criteria
     * @return List of invoices matching the filter
     */
    @GetMapping("")
    public List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getInvoices(invoiceFilter);
    }

    /**
     * Retrieves details of a specific invoice.
     * @param invoiceId The ID of the invoice to retrieve
     * @return InvoiceDTO Details of the specified invoice
     */
    @GetMapping("/{invoiceId}")
    public InvoiceDTO getInvoiceDetail(@PathVariable Long invoiceId) {
        return invoiceService.getInvoiceDetail(invoiceId);
    }

    /**
     * Edits an existing invoice.
     * @param invoiceId The ID of the invoice to edit
     * @param invoiceDTO The updated invoice data
     * @return InvoiceDTO The edited invoice DTO
     */
    @PutMapping("/{invoiceId}")
    public InvoiceDTO editInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.editInvoice(invoiceId, invoiceDTO);
    }

    /**
     * Deletes an existing invoice.
     * @param invoiceId The ID of the invoice to delete
     */
    @DeleteMapping("/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeInvoice(@PathVariable Long invoiceId) {
        invoiceService.removeInvoice(invoiceId);
    }

    /**
     * Retrieves statistics related to invoices.
     * @return Invoice statistics DTO
     */
    @GetMapping("/statistics")
    public InvoiceStatisticsDTO getStatistics() {
        return invoiceService.getStatistics();
    }

}
