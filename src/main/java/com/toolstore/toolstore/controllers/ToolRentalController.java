package com.toolstore.toolstore.controllers;
import com.toolstore.toolstore.dtos.CheckoutRequestDTO;
import com.toolstore.toolstore.dtos.CheckoutResponseDTO;
import com.toolstore.toolstore.exceptions.InvalidParameterException;
import com.toolstore.toolstore.dtos.RentalAgreement;
import com.toolstore.toolstore.services.ToolRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity<CheckoutResponseDTO> checkoutTool(@RequestBody CheckoutRequestDTO requestDTO) {
        if (requestDTO.getRentalDays() < 1) {
            throw new InvalidParameterException("Rental day count must be 1 or greater");
        }

        if (requestDTO.getDiscountPercent() < 0 || requestDTO.getDiscountPercent() > 100) {
            throw new InvalidParameterException("Discount percent must be in the range 0-100");
        }

        RentalAgreement rentalAgreement = toolRentalService.checkoutTool(
                requestDTO.getToolCode(),
                requestDTO.getRentalDays(),
                requestDTO.getDiscountPercent(),
                requestDTO.getCheckoutDate()
        );

        CheckoutResponseDTO responseDTO = new CheckoutResponseDTO();
        responseDTO.setRentalAgreement(rentalAgreement);

        return ResponseEntity.ok(responseDTO);
    }


}
