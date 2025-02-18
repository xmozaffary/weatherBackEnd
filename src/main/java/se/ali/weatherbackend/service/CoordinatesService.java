package se.ali.weatherbackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.ali.weatherbackend.entity.CoordinatesEntity;
import se.ali.weatherbackend.model.CoordinatesResponse;
import se.ali.weatherbackend.repo.CoordinatesRepository;

import java.util.List;

@Service
public class CoordinatesService {
    private final WebClient webclient;
    private final CoordinatesRepository repo;
    @Value("${weather.api.key}")
    private String API_KEY;

    public CoordinatesService(WebClient.Builder webClientBuilder, CoordinatesRepository repo){
        this.webclient = webClientBuilder.baseUrl("http://api.openweathermap.org").build();
        this.repo = repo;
    }

    public Mono<List<CoordinatesResponse>> getCoordinates(String city){
        return this.webclient.get()
                .uri(uriBuilder -> uriBuilder.path("/geo/1.0/direct")
                        .queryParam("q", city)
                        .queryParam("appid", API_KEY)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CoordinatesResponse>>() {})
                .doOnNext(responseList -> {
                   List<CoordinatesEntity> entities = responseList.stream()
                           .map(res -> new CoordinatesEntity.Builder()
                                   .name(res.getName())
                                   .lat(res.getLat())
                                   .lon(res.getLon())
                                   .country(res.getCountry())
                                   .build())
                           .toList();
                   repo.saveAll(entities);
                });
    }

}
