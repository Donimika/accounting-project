package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {
    /**
     * Creates a new invoice.
     * @param invoiceDTO Data from the client in JSON format
     * @return Response confirming the success of the action as a created DTO
     */
    InvoiceDTO createInvoice(InvoiceDTO invoiceDTO);

    /**
     * Retrieves a list of invoices based on the provided filter.
     * @param invoiceFilter Filter criteria for retrieving invoices
     * @return List of invoices matching the filter criteria
     */
    List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter);

    /**
     * Retrieves details of an invoice based on the ID.
     * @param id ID of the invoice to retrieve details for
     * @return InvoiceDTO Information about the specified invoice.
     */
    InvoiceDTO getInvoiceDetail(long id);

    /**
     * Edits an existing invoice based on the provided ID and data.
     * @param id ID of the invoice to edit
     * @param invoiceDTO Data to update the invoice
     * @return InvoiceDTO Updated invoice details
     */
    InvoiceDTO editInvoice(long id,InvoiceDTO invoiceDTO);

    /**
     * Removes an invoice based on the ID.
     * @param id ID of the invoice to remove
     */
    void removeInvoice(long id);

    /**
     * Retrieves statistics related to invoices.
     * @return InvoiceStatisticsDTO related to invoices
     */
    InvoiceStatisticsDTO getStatistics();
}
