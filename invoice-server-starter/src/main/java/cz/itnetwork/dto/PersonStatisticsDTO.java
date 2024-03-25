package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonStatisticsDTO {
    /**
     * Reference Long used because Java handles possible null values from the database and also because of Lombok.
     */
    private Long personId;

    private String personName;

    private Long revenue;
}
