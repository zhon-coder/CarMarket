package com.example.carmarket;

import android.annotation.SuppressLint;

public class CarItem {
    private final int id;
    private final String brand;
    private final String model;
    private final int year;
    private final String category;
    private final String fuelType;
    private final double price;
    private final int photoResId;
    private final int thumbnailResId;

    public CarItem(int id, String brand, String model, int year,
                   String category, String fuelType, double price,
                   int photoResId, int thumbnailResId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.category = category;
        this.fuelType = fuelType;
        this.price = price;
        this.photoResId = photoResId;
        this.thumbnailResId = thumbnailResId;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getPrice() {
        return price;
    }

    public int getPhotoResId() {
        return photoResId;
    }
    public int getThumbnailResId() { return thumbnailResId; }

    @SuppressLint("DefaultLocale")
    public String getFullTitle() {
        return String.format("%s %s [%s] %.2f$",
                brand,model,fuelType,price);
    }
}
