package com.ishita.prog;
import java.util.*;
import java.util.stream.Collectors;

public class StreamApiQuestions {

    public static void main(String[] args) {

        System.out.println("1. Owners having no cars:");

        List<String> nocars = CarRepository.getOwners().stream()
                .filter(o -> o.getCars() == null || o.getCars().isEmpty()).map(Owner::getName).collect(Collectors.toList());
        nocars.forEach(System.out::println);

        System.out.println("\n2. Owner name = Number of cars:");
        Map<String, Integer> ownerCarCount = CarRepository.getOwners().stream().collect(Collectors.toMap(Owner::getName,
                        o -> (o.getCars() == null) ? 0 : o.getCars().size()));

        ownerCarCount.forEach((name, count) ->
                System.out.println(name + " = " + count)
        );

        System.out.println("\n3. Cars not owned by anyone:");

        List<Car> ownedCars = CarRepository.getOwners().stream().filter(o -> o.getCars() != null)
                .flatMap(o -> o.getCars().stream()).collect(Collectors.toList());
        List<Car> unownedCars = CarRepository.getCars().stream().filter(car -> !ownedCars.contains(car))
                .collect(Collectors.toList());
        unownedCars.forEach(System.out::println);
    }
}