package com.wrona.service;

import com.wrona.model.Star;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StarServiceTest {
    private List<Star> stars = new ArrayList<>();
    private List<Star> expectedStars = new ArrayList<>();
    private Map<Long, Integer> expectedStarsGoupedByDistance = new HashMap<>();
    private Collection<Star> expectedUniqueStars = new ArrayList<>();
    @Autowired
    private StarService starService;

    @BeforeEach
    void setUp() {
        Star starA = Star.builder().name("Star A").distance(5L).build(),
                starB = Star.builder().name("Star B").distance(10L).build(),
                starC = Star.builder().name("Star C").distance(1L).build(),
                starD = Star.builder().name("Star D").distance(3L).build(),
                starE = Star.builder().name("Star E").distance(3L).build(),
                starF = Star.builder().name("Star F").distance(3L).build();
        stars = List.of(
                starA, starB, starC, starD
        );
        expectedStars = List.of(
                starC, starD, starA
        );
        expectedStarsGoupedByDistance.put(1L, 1);
        expectedStarsGoupedByDistance.put(3L, 3);
        expectedStarsGoupedByDistance.put(5L, 1);
        expectedStarsGoupedByDistance.put(10L, 1);

        expectedUniqueStars = new HashSet<>(List.of(starA, starB, starC, starD, starE, starF));


    }

    @Test
    void findClosestStars() {
        assertEquals(expectedStars, starService.findClosestStars(stars, 3));
    }

    @Test
    void getNumberOfStarsByDistances() {
        assertEquals(expectedStarsGoupedByDistance, starService.getNumberOfStarsByDistances(stars));
    }

    @Test
    void getUniqueStars() {
        assertEquals(expectedUniqueStars, starService.getUniqueStars(stars));

    }
}