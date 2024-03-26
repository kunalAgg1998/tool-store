package com.toolstore.toolstore.dtos;

public class CheckoutResponseDTO {
    private RentalAgreement rentalAgreement;

    public CheckoutResponseDTO() {
    }

    public CheckoutResponseDTO(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }

    public RentalAgreement getRentalAgreement() {
        return rentalAgreement;
    }

    public void setRentalAgreement(RentalAgreement rentalAgreement) {
        this.rentalAgreement = rentalAgreement;
    }
}
