package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    /**
     * Reference Long used for Lombok compatibility and following usage in mapper implementations.
     */
    @JsonProperty("_id")
    private Long id;

    private int invoiceNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date issued;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date dueDate;

    @NotBlank
    private String product;

    private long price;

    private int vat;

    @NotBlank
    private String note;

    private PersonDTO seller;

    private PersonDTO buyer;


}
