package com.wrona.service;

import com.wrona.model.Star;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StarService {
    public List<Star> findClosestStars(List<Star> stars, int size) {
        return stars.stream()
                .sorted(applySorting())
                .limit(size)
                .collect(Collectors.toList());
    }

        public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
            return stars.stream()
                    .collect(Collectors.groupingBy(
                            Star::getDistance,
                            Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                    ));
        }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        return stars.stream()
                .collect(Collectors.toMap(
                        Star::getName,
                        Function.identity(),
                        (existing, replacement) -> existing,
                        LinkedHashMap::new
                )).values();
    }

    private Comparator<Star> applySorting() {
        return Comparator.comparingLong(Star::getDistance);
    }
}
