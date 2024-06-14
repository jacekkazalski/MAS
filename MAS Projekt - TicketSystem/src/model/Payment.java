package model;

import java.util.Date;

public class Payment extends DataModel {
    private double amount;
    private Date paymentDate;
    public enum paymentMethodEnum{CARD, ONLINE_TRANSFER, BLIK}
    public enum paymentStatusEnum{STARTED, PAID, CANCELLED}
    private paymentMethodEnum paymentMethod;
    private paymentStatusEnum paymentStatus = paymentStatusEnum.STARTED;
    private final Order order;

    public Payment(Order order, double amount, Date paymentDate, paymentMethodEnum paymentMethod) {
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public paymentMethodEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(paymentMethodEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public paymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(paymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String getInfo() {
        return "";
    }

}
