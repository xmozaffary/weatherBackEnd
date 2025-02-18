package se.ali.weatherbackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import se.ali.weatherbackend.model.CoordinatesResponse;
import se.ali.weatherbackend.service.CoordinatesService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/coordinates")
public class CoordinatesController {
    private final CoordinatesService service;

    public CoordinatesController(CoordinatesService service){
        this.service = service;
    }

    @GetMapping("/{city}")
    public Mono<ResponseEntity<List<CoordinatesResponse>>> getCoordinates(@PathVariable String city){
        return service.getCoordinates(city)
                .map(res -> res.isEmpty()
                ? ResponseEntity.notFound().build()
                        : ResponseEntity.ok(res));
    }
}
