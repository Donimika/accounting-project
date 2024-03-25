package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.mapper.InvoiceMapper;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IdentificationServiceImpl implements IdentificationService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    InvoiceMapper invoiceMapper;

    @Override
    public List<InvoiceDTO> getSales(String identificationNumber) {
        List<PersonEntity> persons = fetchPersonsByIdentificationNumber(identificationNumber);
        return mapPersonInvoicesToDTOs(persons, PersonEntity::getSales);
    }

    @Override
    public List<InvoiceDTO> getPurchases(String identificationNumber) {
        List<PersonEntity> persons = fetchPersonsByIdentificationNumber(identificationNumber);
        return mapPersonInvoicesToDTOs(persons, PersonEntity::getPurchases);
    }

    /**
     * Retrieves a list of persons based on their identification number.
     * @param identificationNumber The identification number used to retrieve persons
     * @return List of persons matching the given identification number
     */
    private List<PersonEntity> fetchPersonsByIdentificationNumber(String identificationNumber) {
        return personRepository.findByIdentificationNumber(identificationNumber);
    }

    /**
     * Maps invoices associated with a list of persons to a list of invoice DTOs.
     * @param persons The list of persons whose invoices will be mapped
     * @param invoiceGetter A function to extract invoices from a person entity
     * @return List of invoice DTOs mapped from the invoices associated with the provided list of persons
     */
    private List<InvoiceDTO> mapPersonInvoicesToDTOs(List<PersonEntity> persons, Function<PersonEntity, List<InvoiceEntity>> invoiceGetter) {
        return persons.stream()
                .map(invoiceGetter)
                .flatMap(Collection::stream)
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }
}
