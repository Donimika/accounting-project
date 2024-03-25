package cz.itnetwork.service;


import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return newly created person
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Fetches all non-hidden persons
     *
     * @return List of all non-hidden persons
     */
    List<PersonDTO> getAll();

    /**
     * Retrieves a specific person based on the provided [id].
     *
     * @param id The identifier of the person to retrieve
     * @return The person DTO corresponding to the provided [id]
     */
    PersonDTO getPerson(long id);

    /**
     * Edits a person.
     * @param id ID of the person being edited
     * @param personDTO Adjusted data
     * @return PersonDTO with new data
     */
    PersonDTO editPerson(long id, PersonDTO personDTO);

    /**
     * Retrieves statistics for all persons.
     * @return A list containing statistics for all persons
     */
    List<PersonStatisticsDTO> getStatistics();

}
