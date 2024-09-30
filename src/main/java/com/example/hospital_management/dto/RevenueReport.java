package com.example.hospital_management.dto;

import java.math.BigDecimal;
import java.util.List;

public class RevenueReport {

    private String month;
    private String year;
    private BigDecimal totalRevenue;
    private List<ServiceRevenue> serviceRevenues;

    public RevenueReport(String month, String year, BigDecimal totalRevenue, List<ServiceRevenue> serviceRevenues) {
        this.month = month;
        this.year = year;
        this.totalRevenue = totalRevenue;
        this.serviceRevenues = serviceRevenues;
    }

    // Getters and Setters
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<ServiceRevenue> getServiceRevenues() {
        return serviceRevenues;
    }

    public void setServiceRevenues(List<ServiceRevenue> serviceRevenues) {
        this.serviceRevenues = serviceRevenues;
    }

    // Inner class for Service Revenue details
    public static class ServiceRevenue {
        private String serviceName;
        private BigDecimal revenue;

        public ServiceRevenue(String serviceName, BigDecimal revenue) {
            this.serviceName = serviceName;
            this.revenue = revenue;
        }

        // Getters and Setters
        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public BigDecimal getRevenue() {
            return revenue;
        }

        public void setRevenue(BigDecimal revenue) {
            this.revenue = revenue;
        }
    }
}
