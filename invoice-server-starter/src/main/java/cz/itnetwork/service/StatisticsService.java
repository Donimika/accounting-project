package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;

import java.util.List;

public interface StatisticsService {
    /**
     * Retrieves statistics for persons.
     * @return List of statistics DTOs for persons
     */
    List<PersonStatisticsDTO> getPersonStatistics();

    /**
     * Retrieves statistics for invoices.
     * @return Statistics DTO for invoices
     */
    InvoiceStatisticsDTO getInvoiceStatistics();
}
