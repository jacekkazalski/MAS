package model;

import java.time.LocalDate;

public class Discount {
    private double percentOff;
    private double minOrderValue;
    private LocalDate validFrom;
    private LocalDate validTo;

    public Discount(double percentOff, double minOrderValue, LocalDate validFrom, LocalDate validTo) {
        this.percentOff = percentOff;
        this.minOrderValue = minOrderValue;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(double percentOff) {
        this.percentOff = percentOff;
    }

    public double getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(double minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDate validTo) {
        this.validTo = validTo;
    }
}
