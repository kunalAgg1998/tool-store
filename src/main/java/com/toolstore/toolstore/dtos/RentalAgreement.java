package com.toolstore.toolstore.dtos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a Rental Agreement for a tool rental.
 */
public class RentalAgreement {
    private Tool tool;
    private int rentalDays;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    private BigDecimal dailyRentalCharge;
    private int chargeDays;
    private BigDecimal preDiscountCharge;
    private int discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;
    /**
     * Default constructor for RentalAgreement class.
     */

    public RentalAgreement() {
    }
    /**
     * Parameterized constructor for RentalAgreement class.
     *
     * @param tool              the rented tool
     * @param rentalDays        the number of rental days
     * @param checkoutDate      the checkout date
     * @param dueDate           the due date
     * @param dailyRentalCharge the daily rental charge
     * @param chargeDays        the number of chargeable days
     * @param preDiscountCharge the pre-discount charge
     * @param discountPercent   the discount percentage
     * @param discountAmount    the discount amount
     * @param finalCharge       the final charge after discount
     * @param brandCharge       the brand charge
     */
    public RentalAgreement(Tool tool, int rentalDays, LocalDate checkoutDate, LocalDate dueDate, BigDecimal dailyRentalCharge, int chargeDays, BigDecimal preDiscountCharge, int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge, BigDecimal brandCharge) {
        this.tool = tool;
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
    @JsonIgnore
    public Tool getTool() {
        return tool;
    }
    @JsonProperty("toolCode")
     public String getToolCode(){
        return tool.getCode();
     }
    @JsonProperty("toolType")
    public String getToolType(){
        return tool.getType();
    }
    @JsonProperty("toolBrand")
    public String getToolBrand(){
        return tool.getBrand();
    }


    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getDailyRentalCharge() {
        return dailyRentalCharge;
    }

    public void setDailyRentalCharge(BigDecimal dailyRentalCharge) {
        this.dailyRentalCharge = dailyRentalCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }



      // Function to print the details of Rental Agreement
    public void printDetails() {
        DecimalFormat currencyFormat = new DecimalFormat("$#,##0.00");
        DecimalFormat percentFormat = new DecimalFormat("##%");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        System.out.println("Tool code: " + tool.getCode() + " Tool type: " + tool.getType());
        System.out.println("Brand: " + tool.getBrand());
        System.out.println("Rental days: " + rentalDays);
        System.out.println("Checkout date: " + checkoutDate.format(dateFormatter));
        System.out.println("Due date: " + dueDate.format(dateFormatter));
        System.out.println("Daily rental charge: " + currencyFormat.format(dailyRentalCharge));
        System.out.println("Charge days: " + chargeDays);
        System.out.println("Pre-discount charge: " + currencyFormat.format(preDiscountCharge));
        System.out.println("Discount percent: " + percentFormat.format(discountPercent));
        System.out.println("Discount amount: " + currencyFormat.format(discountAmount));

        System.out.println("Final charge: " + currencyFormat.format(finalCharge));
    }
}
