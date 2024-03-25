/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.mapper.PersonMapper;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StatisticsService statisticsService;

    /**
     * Add new person
     * @param personDTO Data transferred to entity
     * @return PersonDTO including the new ID created in the database
     */
    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.save(entity);
        return personMapper.toDTO(entity);
    }

    /**
     * Delete person from the view(person will be set as hidden in the database)
     * @param personId ID of the person to hide
     */
    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);
            personRepository.save(person);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.
        }
    }

    /**
     * Get list of all persons from database (not hidden ones!)
     * @return List of persons
     */
    @Override
    public List<PersonDTO> getAll() {
        return personRepository.findByHidden(false)
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a person based on the ID.
     * @param id Person ID
     * @return PersonDTO based on the ID
     */
    @Override
    public PersonDTO getPerson(long id) {
        PersonEntity personEntity = fetchPersonById(id);
        return personMapper.toDTO(personEntity);
    }

    /**
     * Updates the details of a person with the specified [id] using the information provided in [personDTO].
     * The original person with the matching [id] will be marked as hidden, and a new person with the updated details will be created.
     * The law requires keeping old data, which is why the ID is changable.
     * @param id        The identifier of the person to update
     * @param personDTO The updated details of the person
     * @return The DTO of the newly updated person
     */
    @Override
    public PersonDTO editPerson(long id, PersonDTO personDTO) {
        PersonEntity fetchedPersonEntity = fetchPersonById(id);
        fetchedPersonEntity.setHidden(true);
        personRepository.save(fetchedPersonEntity);
        PersonEntity newPersonEntity = personMapper.toEntity(personDTO);
        newPersonEntity.setId(null);
        PersonEntity saved = personRepository.save(newPersonEntity);
        return personMapper.toDTO(saved);
    }

    /**
     * Retrieves statistics for persons from the statistics service.
     * If person were edited, statistics won't be accurate because of missing non-changeable identifier.
     * @return List of person statistics DTOs
     */
    @Override
    public List<PersonStatisticsDTO> getStatistics() {
        return statisticsService.getPersonStatistics();
    }


    // region: Private methods

    /**
     * <p>Attempts to fetch a person.</p>
     * <p>In case a person with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.</p>
     *
     * @param id Person to fetch
     * @return Fetched entity
     * @throws org.webjars.NotFoundException In case a person with the passed [id] isn't found
     */
    private PersonEntity fetchPersonById(long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }
    // endregion
}
