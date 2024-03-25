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
package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    @Autowired
    private PersonService personService;

    /**
     * Adds a new person.
     * @param personDTO The person data to add
     * @return PersonDTO The newly created person DTO
     */
    @PostMapping("")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return personService.addPerson(personDTO);
    }

    /**
     * Retrieves all persons.
     * @return List of all persons
     */
    @GetMapping("")
    public List<PersonDTO> getPersons() {
        return personService.getAll();
    }

    /**
     * Deletes a person.
     * @param personId The ID of the person to delete
     */
    @DeleteMapping("/{personId}")
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }

    /**
     * Retrieves details of a specific person.
     * @param personId The ID of the person to retrieve
     * @return PersonDTO Details of the specified person
     */
    @GetMapping("/{personId}")
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personService.getPerson(personId);
    }

    /**
     * Edits an existing person.
     * @param personId The ID of the person to edit
     * @param personDTO The updated person data
     * @return PersonDTO The edited person DTO
     */
    @PutMapping("/{personId}")
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO) {
        return personService.editPerson(personId, personDTO);
    }

    /**
     * Retrieves statistics related to persons.
     * @return List of person statistics DTOs
     */
    @GetMapping("/statistics")
    public List<PersonStatisticsDTO> getStatistics() {
        return personService.getStatistics();
    }
}

