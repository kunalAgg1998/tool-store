package com.toolstore.toolstore.dtos;

public class RentalAgreementResponseDTO {

        private String toolCode;
        private String toolType;
        private String toolBrand;
        private int rentalDays;
        private String checkoutDate;
        private String dueDate;
        private String dailyRentalCharge;
        private int chargeDays;
        private String preDiscountCharge;
        private int discountPercent;
        private String discountAmount;
        private String finalCharge;

        // Constructor
        public RentalAgreementResponseDTO() {
        }
    public RentalAgreementResponseDTO(String toolCode, String toolType, String toolBrand, int rentalDays, String checkoutDate, String dueDate, String dailyRentalCharge, int chargeDays, String preDiscountCharge, int discountPercent, String discountAmount, String finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyRentalCharge = dailyRentalCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }
    // Getter and setter methods

        public String getToolCode() {
            return toolCode;
        }

        public void setToolCode(String toolCode) {
            this.toolCode = toolCode;
        }

        public String getToolType() {
            return toolType;
        }

        public void setToolType(String toolType) {
            this.toolType = toolType;
        }

        public String getToolBrand() {
            return toolBrand;
        }

        public void setToolBrand(String toolBrand) {
            this.toolBrand = toolBrand;
        }

        public int getRentalDays() {
            return rentalDays;
        }

        public void setRentalDays(int rentalDays) {
            this.rentalDays = rentalDays;
        }

        public String getCheckoutDate() {
            return checkoutDate;
        }

        public void setCheckoutDate(String checkoutDate) {
            this.checkoutDate = checkoutDate;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public String getDailyRentalCharge() {
            return dailyRentalCharge;
        }

        public void setDailyRentalCharge(String dailyRentalCharge) {
            this.dailyRentalCharge = dailyRentalCharge;
        }

        public int getChargeDays() {
            return chargeDays;
        }

        public void setChargeDays(int chargeDays) {
            this.chargeDays = chargeDays;
        }

        public String getPreDiscountCharge() {
            return preDiscountCharge;
        }

        public void setPreDiscountCharge(String preDiscountCharge) {
            this.preDiscountCharge = preDiscountCharge;
        }

        public int getDiscountPercent() {
            return discountPercent;
        }

        public void setDiscountPercent(int discountPercent) {
            this.discountPercent = discountPercent;
        }

        public String getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
        }

        public String getFinalCharge() {
            return finalCharge;
        }

        public void setFinalCharge(String finalCharge) {
            this.finalCharge = finalCharge;
        }

        // Method to map rental agreement details
        public void mapRentalAgreementDetails(RentalAgreementDTO rentalAgreementDTO) {
            this.toolCode = rentalAgreementDTO.getToolCode();
            this.toolType = rentalAgreementDTO.getToolType();
            this.toolBrand = rentalAgreementDTO.getToolBrand();
            this.rentalDays = rentalAgreementDTO.getRentalDays();
            this.checkoutDate = rentalAgreementDTO.getFormattedCheckoutDate();
            this.dueDate = rentalAgreementDTO.getFormattedDueDate();
            this.dailyRentalCharge = rentalAgreementDTO.getDailyRentalCharge().toString();
            this.chargeDays = rentalAgreementDTO.getChargeDays();
            this.preDiscountCharge = rentalAgreementDTO.getPreDiscountCharge().toString();
            this.discountPercent = rentalAgreementDTO.getDiscountPercent();
            this.discountAmount = rentalAgreementDTO.getDiscountAmount().toString();
            this.finalCharge = rentalAgreementDTO.getFinalCharge().toString();
        }
    }


