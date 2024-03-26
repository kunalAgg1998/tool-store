package com.toolstore.toolstore.dtos;

import java.time.LocalDate;

public class CheckoutRequestDTO {
    private String toolCode;
    private int rentalDays;
    private int discountPercent;
    private LocalDate checkoutDate;

    public CheckoutRequestDTO() {
    }

    public CheckoutRequestDTO(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
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

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }
}
