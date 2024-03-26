package com.toolstore.toolstore.services;

import com.toolstore.toolstore.exceptions.InvalidParameterException;
import com.toolstore.toolstore.dtos.RentalAgreement;
import com.toolstore.toolstore.dtos.Tool;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class ToolRentalServiceImpl implements ToolRentalService {

    private final Map<String, Tool> toolMap = new HashMap<>();
    private final Set<LocalDate> holidaySet = new HashSet<>();
    private final int WEEKEND_DAYS = 2;
    public ToolRentalServiceImpl() {
        populateToolMap();
        populateHolidaySet();
    }

    private void populateToolMap() {
        toolMap.put("LADW", new Tool("LADW", "LADDER", "Werner", new BigDecimal("1.99"), true, true, false));
        toolMap.put("CHNS", new Tool("CHNS", "CHAINSAW", "Stihl", new BigDecimal("1.49"), true, false, true));
        toolMap.put("JAKD", new Tool("JAKD", "JACKHAMMER", "DeWalt", new BigDecimal("2.99"), true, false, false));
        toolMap.put("JAKR", new Tool("JAKR", "JACKHAMMER", "Ridgid", new BigDecimal("2.99"), true, false, false));
    }

    private void populateHolidaySet() {
        holidaySet.add(LocalDate.of(2024, Month.JULY, 4)); // Independence Day
        holidaySet.add(LocalDate.of(2024, Month.SEPTEMBER, 2)); // Labor Day
    }

    @Override
    public RentalAgreement checkoutTool(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        Tool tool = toolMap.get(toolCode);
        if (tool == null) {
            throw new InvalidParameterException("Invalid tool code");
        }
        if (rentalDays < 1) {
            throw new InvalidParameterException("Rental day count must be 1 or greater");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new InvalidParameterException("Discount percent must be in the range 0-100");
        }

        BigDecimal brandCharge = calculateBrandCharge(tool, checkoutDate);
        LocalDate dueDate = checkoutDate.plusDays(rentalDays);
        BigDecimal dailyRentalCharge = tool.getDailyCharge();
        int chargeDays = calculateChargeDays(checkoutDate, dueDate, tool);
        BigDecimal preDiscountCharge = dailyRentalCharge.multiply(BigDecimal.valueOf(chargeDays));
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent / 100.0));
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);

        return new RentalAgreement(tool, rentalDays, checkoutDate, dueDate, dailyRentalCharge, chargeDays,
                preDiscountCharge, discountPercent, discountAmount, finalCharge, brandCharge);
    }

    private BigDecimal calculateBrandCharge(Tool tool, LocalDate checkoutDate) {
        boolean isWeekday = checkoutDate.getDayOfWeek() != DayOfWeek.SATURDAY &&
                checkoutDate.getDayOfWeek() != DayOfWeek.SUNDAY;
        boolean isWeekend = !isWeekday;
        boolean isHoliday = isHoliday(checkoutDate);

        BigDecimal brandCharge = BigDecimal.ZERO;
        if ((isWeekday && tool.isWeekdayCharge()) || (isWeekend && tool.isWeekendCharge()) ||
                (isHoliday && tool.isHolidayCharge())) {
            brandCharge = tool.getDailyCharge();
        }
        return brandCharge;
    }


    private int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, Tool tool) {
        long weekendDays = checkoutDate.datesUntil(dueDate.plusDays(1)).filter(this::isWeekend).count();
        long holidayDays = checkoutDate.datesUntil(dueDate.plusDays(1)).filter(this::isHoliday).count();
        long chargeableDays = dueDate.toEpochDay() - checkoutDate.toEpochDay() + 1 - weekendDays - holidayDays;
        return Math.toIntExact(chargeableDays);
    }

    private boolean isChargeableDay(LocalDate date, Tool tool) {
        return !isHoliday(date) && (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY);
    }
    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        return holidaySet.contains(date);
    }
}
