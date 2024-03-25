package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.entity.repository.InvoiceRepository;
import cz.itnetwork.entity.repository.PersonRepository;
import cz.itnetwork.entity.repository.specification.InvoiceSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceMapper invoiceMapper;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    PersonMapper personMapper;
    @Autowired
    StatisticsService statisticsService;


    @Override
    public InvoiceDTO createInvoice(InvoiceDTO invoiceDTO) {
        // Map DTO to entity and save it to the repository
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        InvoiceEntity saved = invoiceRepository.save(entity);
        // Map the saved entity back to DTO
        InvoiceDTO dto = invoiceMapper.toDTO(saved);
        // Retrieve buyer and seller details and set them in the DTO
        PersonEntity personBuyer = fetchPersonById(dto.getBuyer().getId());
        PersonEntity personSeller = fetchPersonById(dto.getSeller().getId());
        dto.setBuyer(personMapper.toDTO(personBuyer));
        dto.setSeller(personMapper.toDTO(personSeller));
        return dto;
    }


    @Override
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {
        // Create a specification based on the provided filter and retrieve invoices from the repository
        InvoiceSpecification invoiceSpecification = new InvoiceSpecification(invoiceFilter);
        return invoiceRepository.findAll(invoiceSpecification, PageRequest.of(0, invoiceFilter.getLimit()))
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public InvoiceDTO getInvoiceDetail(long id) {
        // Fetch and map the invoice details based on the ID
        return invoiceMapper.toDTO(fetchInvoiceById(id));
    }


    @Override
    public InvoiceDTO editInvoice(long id, InvoiceDTO invoiceDTO) {
        // Check if the invoice exists, then update it and save the changes
        if (!invoiceRepository.existsById(id)) {
            throw new EntityNotFoundException("Invoice with id " + id + " doesn't exist.");
        }
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        entity.setId(id);
        InvoiceEntity saved = invoiceRepository.save(entity);
        return invoiceMapper.toDTO(saved);
    }


    @Override
    public void removeInvoice(long id) {
        // Remove the invoice based on the ID
        InvoiceEntity entity = fetchInvoiceById(id);
        invoiceRepository.delete(entity);
    }

    @Override
    public InvoiceStatisticsDTO getStatistics() {
        // Delegate the retrieval of invoice statistics to the statistics service
        return statisticsService.getInvoiceStatistics();
    }


    /**
     * <p>Attempts to fetch a person.</p>
     * <p>In case a person with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.</p>
     *
     * Reason for code duplication
     * Keeping the method within the service implementations simplifies the codebase.
     * @param id Person to fetch
     * @return Fetched entity
     */

    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }

    /**
     * Attempts to fetch an invoice.
     * @param id Invoice ID to fetch
     * @return Fetched entity
     */
    private InvoiceEntity fetchInvoiceById(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }
}
