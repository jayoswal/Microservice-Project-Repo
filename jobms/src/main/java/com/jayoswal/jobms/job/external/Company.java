package com.jayoswal.jobms.job.external;

public class Company {
    private int id;
    private String name;
    private String description;
    private int numberOfReviews;
    private double averageRating;

    public Company() {
    }

    public Company(int id, String name, String description, int numberOfReviews, double averageRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numberOfReviews = numberOfReviews;
        this.averageRating = averageRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberOfReviews=" + numberOfReviews +
                ", averageRating=" + averageRating +
                '}';
    }
}
