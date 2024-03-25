package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    PersonRepository personRepository;

    /**
     * This method fetches invoice statistics from the {@code invoiceRepository} and ensures that any null values
     * in the statistics are replaced with default values (0L) to prevent null pointer exceptions.
     * @return Statistics related to invoices
     */
    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        InvoiceStatisticsDTO statistics = invoiceRepository.getStatistics();
        Stream.of(statistics)
                .forEach(s -> {
                    s.setCurrentYearSum(s.getCurrentYearSum() != null ? s.getCurrentYearSum() : 0L);
                    s.setAllTimeSum(s.getAllTimeSum() != null ? s.getAllTimeSum() : 0L);
                    s.setInvoicesCount(s.getInvoicesCount() != null ? s.getInvoicesCount() : 0L);
                });
        return statistics;
    }

    /**
     * Retrieves statistics for non-hidden persons.
     * Each entry in the returned list represents statistics for an individual person,
     * including their ID, name, and revenue generated from associated invoices.
     * @return List of statistics for persons
     */
    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personRepository.findByHidden(false)
                .stream()
                .map(p -> new PersonStatisticsDTO(p.getId(), p.getName(), invoiceRepository.getRevenue(p.getId()) != null ? invoiceRepository.getRevenue(p.getId()) : 0L))
                .collect(Collectors.toList());
    }
}
