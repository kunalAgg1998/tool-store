package com.toolstore.toolstore.controllers;
import com.toolstore.toolstore.dtos.CheckoutRequestDTO;
import com.toolstore.toolstore.dtos.RentalAgreementResponseDTO;
import com.toolstore.toolstore.exceptions.InvalidParameterException;
import com.toolstore.toolstore.dtos.RentalAgreementDTO;
import com.toolstore.toolstore.services.ToolRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller class for handling tool rental operations.
 */
@RestController
public class ToolRentalController {
    @Autowired
    private ToolRentalService toolRentalService;

    /**
     * Endpoint for checking out a tool.
     * @return ResponseEntity containing the rental agreement information
     */
    @PostMapping("/checkout")
    public ResponseEntity<RentalAgreementResponseDTO> checkoutTool(@RequestBody CheckoutRequestDTO requestDTO) {
        if (requestDTO.getRentalDays() < 1) {
            throw new InvalidParameterException("Rental day count must be 1 or greater");
        }

        if (requestDTO.getDiscountPercent() < 0 || requestDTO.getDiscountPercent() > 100) {
            throw new InvalidParameterException("Discount percent must be in the range 0-100");
        }
        String checkoutDateString = requestDTO.getCheckoutDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate checkoutDate = LocalDate.parse(checkoutDateString, formatter);

        RentalAgreementDTO rentalAgreementDTO = toolRentalService.checkoutTool(
                requestDTO.getToolCode(),
                requestDTO.getRentalDays(),
                requestDTO.getDiscountPercent(),
                checkoutDate
        );
        rentalAgreementDTO.printDetails();
        RentalAgreementResponseDTO responseDTO = new RentalAgreementResponseDTO();
        responseDTO.mapRentalAgreementDetails(rentalAgreementDTO);
        return ResponseEntity.ok(responseDTO);
    }


}
