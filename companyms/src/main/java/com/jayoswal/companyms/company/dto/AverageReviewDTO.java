package com.jayoswal.companyms.company.dto;

public class AverageReviewDTO {
    private int numberOfReviews;
    private double averageRating;

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "AverageReviewDTO{" +
                "numberOfReviews=" + numberOfReviews +
                ", averageRating=" + averageRating +
                '}';
    }
}
