package com.toolstore.toolstore.services;

import com.toolstore.toolstore.dtos.ToolDTO;
import com.toolstore.toolstore.exceptions.InvalidParameterException;
import com.toolstore.toolstore.dtos.RentalAgreementDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ToolRentalServiceImpl implements ToolRentalService {

    // Map to store tools by their code
    private final Map<String, ToolDTO> toolMap = new HashMap<>();
    // Set to store holiday dates
    private final Set<LocalDate> holidaySet = new HashSet<>();
    // Number of weekend days (Saturday and Sunday)
    private final int WEEKEND_DAYS = 2;

    // Constructor to initialize the tool map and holiday set
    public ToolRentalServiceImpl() {
        populateToolMap();
        populateHolidaySet();
    }
    // Method to populate the tool map with tool information
    private void populateToolMap() {
        toolMap.put("LADW", new ToolDTO("LADW", "LADDER", "Werner", new BigDecimal("1.99"), true, true, false));
        toolMap.put("CHNS", new ToolDTO("CHNS", "CHAINSAW", "Stihl", new BigDecimal("1.49"), true, false, true));
        toolMap.put("JAKD", new ToolDTO("JAKD", "JACKHAMMER", "DeWalt", new BigDecimal("2.99"), true, false, false));
        toolMap.put("JAKR", new ToolDTO("JAKR", "JACKHAMMER", "Ridgid", new BigDecimal("2.99"), true, false, false));
    }
    // Method to populate the holiday set with holiday dates
    private void populateHolidaySet() {
        // Independence Day (July 4th)
        LocalDate independenceDay = LocalDate.of(LocalDate.now().getYear(), Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1); // Observed on Friday before
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1); // Observed on Monday after
        }
        holidaySet.add(independenceDay);

        // Labor Day (First Monday in September)
        LocalDate laborDay = LocalDate.of(LocalDate.now().getYear(), Month.SEPTEMBER, 1);
        while (laborDay.getDayOfWeek() != DayOfWeek.MONDAY) {
            laborDay = laborDay.plusDays(1);
        }
        holidaySet.add(laborDay);
    }

    // Method to process tool checkout
    @Override
    public RentalAgreementDTO checkoutTool(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        ToolDTO toolDTO = toolMap.get(toolCode);
        if (toolDTO == null) {
            throw new InvalidParameterException("Invalid tool code");
        }
        if (rentalDays < 1) {
            throw new InvalidParameterException("Rental day count must be 1 or greater");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidParameterException("Discount percent must be in the range 0-100");
        }

        BigDecimal brandCharge = calculateBrandCharge(toolDTO, checkoutDate); // Calculate the brand charge for the tool
        LocalDate dueDate = checkoutDate.plusDays(rentalDays); // Calculate the due date based on the rental days
        BigDecimal dailyRentalCharge = toolDTO.getDailyCharge(); // Get the daily rental charge for the tool
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, toolDTO); // Calculate the number of chargeable days
        BigDecimal preDiscountCharge = dailyRentalCharge.multiply(BigDecimal.valueOf(chargeDays)); // Calculate the pre-discount charge
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent / 100.0)); // Calculate the discount amount
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount); // Calculate the final charge after discount

        // Create and return a new RentalAgreementDTO object with the generated rental agreement details
        return new RentalAgreementDTO(toolDTO, rentalDays, checkoutDate, dueDate, dailyRentalCharge, chargeDays,
                preDiscountCharge, discountPercent, discountAmount, finalCharge, brandCharge);
    }
    // Method to calculate brand charge based on checkout date and tool type
    // The brand charge is determined based on the tool's pricing policies and the checkout date
    private BigDecimal calculateBrandCharge(ToolDTO toolDTO, LocalDate checkoutDate) {
        // Check if the checkout date falls on a weekday, weekend, or holiday

        boolean isWeekday = checkoutDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                checkoutDate.getDayOfWeek() != DayOfWeek.SUNDAY;
        boolean isWeekend = !isWeekday;
        boolean isHoliday = isHoliday(checkoutDate);

        BigDecimal brandCharge = BigDecimal.ZERO; // Initialize brand charge to zero

        // Determine the brand charge based on the pricing policies of the tool and the checkout date
        if ((isWeekday && toolDTO.isWeekdayCharge()) || (isWeekend && toolDTO.isWeekendCharge()) ||
                (isHoliday && toolDTO.isHolidayCharge())) {
            brandCharge = toolDTO.getDailyCharge(); // Set brand charge to the tool's daily charge
        }
        return brandCharge; // Return the calculated brand charge
    }


    // Method to calculate the number of chargeable days for tool rental
    // It calculates the number of days between checkoutDate and dueDate excluding weekends and holidays
    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, ToolDTO toolDTO) {
        // Count the number of weekend days between checkoutDate and dueDate
        long weekendDays = checkoutDate.datesUntil(dueDate.plusDays(1)).filter(this::isWeekend).count();
        // Count the number of holiday days between checkoutDate and dueDate
        long holidayDays = checkoutDate.datesUntil(dueDate.plusDays(1)).filter(this::isHoliday).count();
        // Calculate the total chargeable days by subtracting weekends and holidays from the total days
        long chargeableDays = dueDate.toEpochDay() - checkoutDate.toEpochDay() + 1 - weekendDays - holidayDays;
        // Convert the long value to int (as chargeableDays is expected to be an int)
        return Math.toIntExact(chargeableDays);
    }
    // Method to check if a given date is a chargeable day (not a weekend or holiday)
    private boolean isChargeableDay(LocalDate date, ToolDTO toolDTO) {
        return !isHoliday(date) && (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY);
    }
    // Method to check if a given date is a weekend (Saturday or Sunday)
    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    // Method to check if a given date is a holiday
    private boolean isHoliday(LocalDate date) {
        return holidaySet.contains(date);
    }
}
