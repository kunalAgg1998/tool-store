package com.toolstore.toolstore;

import com.toolstore.toolstore.exceptions.InvalidParameterException;
import com.toolstore.toolstore.dtos.RentalAgreement;
import com.toolstore.toolstore.services.ToolRentalService;
import com.toolstore.toolstore.services.ToolRentalServiceImpl;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
/**
 * Unit tests for the ToolRentalService.
 */
public class ToolRentalServiceTest {
    private final ToolRentalService toolRentalService;
    /**
     * Constructs a new ToolRentalServiceTest and initializes the toolRentalService with a new instance
     * of ToolRentalServiceImpl.
     */
    public ToolRentalServiceTest() {
        // Initialize the toolRentalService with a new instance of ToolRentalServiceImpl
        this.toolRentalService = new ToolRentalServiceImpl();
    }
    /**
     * Tests the checkoutTool method with valid parameters.
     */
    @Test
    public void testToolRentalWithValidParameters() {
        // Test 1: Valid parameters
        RentalAgreement rentalAgreement = toolRentalService.checkoutTool("JAKR", 5, 0, LocalDate.of(2015, 9, 3));
        assertEquals("JAKR", rentalAgreement.getTool().getCode());
        // Add more assertions as needed
    }
    /**
     * Tests the checkoutTool method with invalid rental days.
     */
    @Test
    public void testToolRentalWithInvalidRentalDays() {
        // Test 2: Rental days less than 1
        assertThrows(InvalidParameterException.class, () ->
                toolRentalService.checkoutTool("LADW", 0, 10, LocalDate.of(2020, 7, 2)));
    }
    /**
     * Tests the checkoutTool method with invalid discount percentage.
     */
    @Test
    public void testToolRentalWithInvalidDiscount() {
        // Test 3: Discount percent greater than 100
        assertThrows(InvalidParameterException.class, () ->
                toolRentalService.checkoutTool("CHNS", 5, 101, LocalDate.of(2015, 7, 2)));
    }
    /**
     * Tests the checkoutTool method with valid parameters for various scenarios.
     */
    @Test
    public void testToolRentalWithValidParametersForAllScenarios() {
        // Test 4: Valid parameters (scenario 4)
        RentalAgreement rentalAgreement4 = toolRentalService.checkoutTool("JAKD", 6, 0, LocalDate.of(2015, 9, 3));
        assertNotNull(rentalAgreement4);
        assertEquals("JAKD", rentalAgreement4.getTool().getCode());
        assertEquals(6, rentalAgreement4.getRentalDays());

        // Test 5: Valid parameters (scenario 5)
        RentalAgreement rentalAgreement5 = toolRentalService.checkoutTool("JAKR", 9, 0, LocalDate.of(2015, 7, 2));
        assertNotNull(rentalAgreement5);
        assertEquals("JAKR", rentalAgreement5.getTool().getCode());
        assertEquals(9, rentalAgreement5.getRentalDays());

        // Test 6: Valid parameters (scenario 6)
        RentalAgreement rentalAgreement6 = toolRentalService.checkoutTool("JAKR", 4, 50, LocalDate.of(2020, 7, 2));
        assertNotNull(rentalAgreement6);
        assertEquals("JAKR", rentalAgreement6.getTool().getCode());
        assertEquals(4, rentalAgreement6.getRentalDays());
        assertEquals(50, rentalAgreement6.getDiscountPercent());
    }
}
