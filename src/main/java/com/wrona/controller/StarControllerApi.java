package com.wrona.controller;

import com.wrona.model.Star;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RequestMapping("/star/controller")
public interface StarControllerApi {
    @GetMapping("/get")
    ResponseEntity<Star> getStartById(@RequestParam Long id);

    @PostMapping("/post")
    ResponseEntity<String> addStar(@RequestParam String name, @RequestParam Long distance);

    @PutMapping("/put")
    ResponseEntity<Star> updateStar(@RequestParam Long id, @RequestParam String name, @RequestParam Long distance);

    @DeleteMapping("/delete")
    ResponseEntity <Void> deleteStar(@RequestParam Long id);

    @GetMapping("/get-closest-star")
    ResponseEntity<List<Star>> getClosestStar(@RequestParam List<Star> stars, @RequestParam int size);

    @GetMapping("/get-number-of-stars-by-distance")
    ResponseEntity <Map<Long, Integer>> getNumberOfStarsByDistances(@RequestParam List<Star> stars);

    @GetMapping("/get-unique-stars")
    ResponseEntity<Collection<Star>> getUniqueStars(@RequestParam Collection<Star> stars);
}
