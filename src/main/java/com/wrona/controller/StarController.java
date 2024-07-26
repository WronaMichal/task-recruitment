package com.wrona.controller;

import com.wrona.exception.StarException;
import com.wrona.model.Star;
import com.wrona.repository.StarRepository;
import com.wrona.service.StarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class StarController implements StarControllerApi{
    private final StarService starService;
    private final StarRepository starRepository;


    @Override
    public ResponseEntity<Star> getStartById(Long id) {
        return new ResponseEntity<>(starRepository.findById(id).orElseThrow(()->new StarException("Star not found")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> addStar(String name, Long distance) {
        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>("Name cannot be null or empty", HttpStatus.BAD_REQUEST);
        }
        if (distance == null || distance < 0) {
            return new ResponseEntity<>("Distance must be a positive value", HttpStatus.BAD_REQUEST);
        }
        Star newStar = new Star(name, distance);
        starRepository.save(newStar);
        return new ResponseEntity<>("Star added successfully with id: " + newStar.getId(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Star> updateStar(Long id, String name, Long distance) {
        Star star = starRepository.findById(id).orElseThrow(() -> new StarException("Star not found in database"));
        star.setName(name);
        star.setDistance(distance);
        Star updatedStar = starRepository.save(star);
        return new ResponseEntity<>(updatedStar, HttpStatus.OK);
    }

    @Override
    public ResponseEntity <Void> deleteStar(Long id) {
        Star star = starRepository.findById(id).orElseThrow(() -> new StarException("Star not found in database"));
        starRepository.delete(star);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List <Star>> getClosestStar(List<Star> stars, int size) {
        return new ResponseEntity<>(starService.findClosestStars(stars, size), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Long, Integer>> getNumberOfStarsByDistances(List<Star> stars) {
        return new ResponseEntity<>(starService.getNumberOfStarsByDistances(stars), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Collection<Star>> getUniqueStars(Collection<Star> stars) {
        return new ResponseEntity<>(starService.getUniqueStars(stars), HttpStatus.OK);
    }
}
