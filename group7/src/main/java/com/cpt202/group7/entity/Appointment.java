package com.cpt202.group7.entity;

import com.cpt202.group7.enumerator.AppointmentState;
import com.cpt202.group7.enumerator.PayMethod;

import java.sql.Timestamp;

public class Appointment {
    private Timestamp createTime;
    private Timestamp appointmentTime;

    private Service service;
    private Groomer groomer;
    private Pet pet;

    private Double beforeDiscount;
    private Double discountCoefficient;
    private Double afterDiscount;

    private PayMethod payMethod;
    private AppointmentState appointmentState;

    public Appointment(Timestamp createTime, Timestamp appointmentTime, Service service, Groomer groomer, Pet pet, Double beforeDiscount, Double discountCoefficient, Double afterDiscount, PayMethod payMethod, AppointmentState appointmentState) {
        this.createTime = createTime;
        this.appointmentTime = appointmentTime;
        this.service = service;
        this.groomer = groomer;
        this.pet = pet;
        this.beforeDiscount = beforeDiscount;
        this.discountCoefficient = discountCoefficient;
        this.afterDiscount = afterDiscount;
        this.payMethod = payMethod;
        this.appointmentState = appointmentState;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Groomer getGroomer() {
        return groomer;
    }

    public void setGroomer(Groomer groomer) {
        this.groomer = groomer;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Double getBeforeDiscount() {
        return beforeDiscount;
    }

    public void setBeforeDiscount(Double beforeDiscount) {
        this.beforeDiscount = beforeDiscount;
    }

    public Double getDiscountCoefficient() {
        return discountCoefficient;
    }

    public void setDiscountCoefficient(Double discountCoefficient) {
        this.discountCoefficient = discountCoefficient;
    }

    public Double getAfterDiscount() {
        return afterDiscount;
    }

    public void setAfterDiscount(Double afterDiscount) {
        this.afterDiscount = afterDiscount;
    }

    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    public AppointmentState getAppointmentState() {
        return appointmentState;
    }

    public void setAppointmentState(AppointmentState appointmentState) {
        this.appointmentState = appointmentState;
    }
}
