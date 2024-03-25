package cz.itnetwork.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticsDTO {
    /**
     * Reference Long used because Java handles possible null values from the database and also because of Lombok.
     */
    private Long currentYearSum;

    private Long allTimeSum;

    private Long invoicesCount;
}
