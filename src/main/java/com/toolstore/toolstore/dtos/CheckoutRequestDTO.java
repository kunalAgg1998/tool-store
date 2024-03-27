package com.toolstore.toolstore.dtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckoutRequestDTO {
    private String toolCode;
    private int rentalDays;
    private int discountPercent;
    private String checkoutDate;

    public CheckoutRequestDTO() {
    }

    public CheckoutRequestDTO(String toolCode, int rentalDays, int discountPercent, String checkoutDate) {
        this.toolCode = toolCode;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
    // Method to format LocalDate checkoutDate to String

}
