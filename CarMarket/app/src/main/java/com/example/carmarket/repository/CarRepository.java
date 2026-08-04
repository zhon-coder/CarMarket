package com.example.carmarket.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carmarket.CarItem;
import com.example.carmarket.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarRepository {

    @NonNull
    public static List<CarItem> search(String keyWord) {
        List<CarItem> result = new ArrayList<>();

        keyWord = keyWord.toLowerCase();
        for(CarItem car : CARS) {
            if(car.getBrand().toLowerCase().contains(keyWord) ||
                    car.getModel().toLowerCase().contains(keyWord)) {
                result.add(car);
            }
        }
        return result;
    }

    @NonNull
    public static List<CarItem> filterByCategory(String category) {
        List<CarItem> result = new ArrayList<>();
        for(CarItem car : CARS) {
            if(car.getCategory().equals(category)) {
                result.add(car);
            }
        }
        return result;
    }

    @NonNull
    public static List<CarItem> filterByFuelType(String fuelType) {
        List<CarItem> result = new ArrayList<>();

        return result;
    }


    @NonNull
    public static List<CarItem> filterByBrand(String brand) {
        List<CarItem> result = new ArrayList<>();

        return result;
    }

    @NonNull
    public static List<CarItem> getAllCars() {
        return Arrays.asList(CARS);
    }

    @Nullable
    public static CarItem getCarItem(int id) {
        for(CarItem item : CARS) {
            if(item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private static final CarItem[] CARS = {
            new CarItem(
                    1,
                    "Toyota",
                    "Camry",
                    2022,
                    "Sedan",
                    "Petrol",
                    25800,
                    R.drawable.carmy,
                    R.drawable.carmy_thumbnail
            ),

            new CarItem(
                    2,
                    "Honda",
                    "Civic",
                    2021,
                    "Sedan",
                    "Petrol",
                    22800,
                    R.drawable.civic,
                    R.drawable.civic_thumbnail
            ),

            new CarItem(
                    3,
                    "Tesla",
                    "Model 3",
                    2023,
                    "EV",
                    "Electric",
                    46800,
                    R.drawable.model3,
                    R.drawable.model3_thumbnail
            ),

            new CarItem(
                    4,
                    "BMW",
                    "X5",
                    2022,
                    "SUV",
                    "Hybrid",
                    68800,
                    R.drawable.bmw_x5,
                    R.drawable.bmw_x5_thumbnail
            ),

            new CarItem(
                    5,
                    "Mercedes-Benz",
                    "C200",
                    2021,
                    "Sedan",
                    "Petrol",
                    51800,
                    R.drawable.c200,
                    R.drawable.c200_thumbnail
            ),

            new CarItem(
                    6,
                    "Ford",
                    "Ranger",
                    2022,
                    "Truck",
                    "Diesel",
                    43800,
                    R.drawable.ranger,
                    R.drawable.ranger_thumbnail
            ),

            new CarItem(
                    7,
                    "Hyundai",
                    "Ioniq 5",
                    2023,
                    "EV",
                    "Electric",
                    49800,
                    R.drawable.ioniq5,
                    R.drawable.ioniq5_thumbnail
            ),

            new CarItem(

                    8,
                    "Kia",
                    "Sportage",
                    2022,
                    "SUV",
                    "Hybrid",
                    34800,
                    R.drawable.sportage,
                    R.drawable.sportage_thumbnail
            ),

            new CarItem(
                    9,
                    "Volkswagen",
                    "Golf",
                    2021,
                    "Hatchback",
                    "Petrol",
                    24800,
                    R.drawable.golf,
                    R.drawable.golf_thumbnail
            ),
            new CarItem(
                    10,
                    "BYD",
                    "Seal",
                    2024,
                    "EV",
                    "Electric",
                    42800,
                    R.drawable.bydseal,
                    R.drawable.bydseal_thumbnail
            )
    };
}
