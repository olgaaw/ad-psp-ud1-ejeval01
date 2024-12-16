package com.salesianostriana.dam.resteval;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RequestMapping("/place/")
public class PlaceController {
    private PlaceRepository placeRepository;

    @GetMapping
    public ResponseEntity<Place> getAll(){
        if (placeRepository.getAll().isEmpty()) {
            return (ResponseEntity<Place>) ResponseEntity.notFound();
        }
        List<Place> places = placeRepository.getAll();
        return ResponseEntity.status();

    }

    @GetMapping("${id}")
    public ResponseEntity<Place> getById(@PathVariable Long id) {
        if (placeRepository.get(id).isPresent()) {

            return ResponseEntity.ok();
        }

        return (ResponseEntity<Place>) ResponseEntity.notFound();

    }

    @PostConstruct
    public ResponseEntity<Place> add(@RequestParam String name, String address, String coords, String desc, String image) {
         Place.builder()
                .name(name)
                .address(address)
                .coords(coords)
                .desc(desc)
                .image(image)
                .build();

         return ResponseEntity.created();

    }

    @DeleteMapping("${id}")
    public ResponseEntity<Place> delete(@PathVariable Long id) {
        placeRepository.delete(id);
        return ResponseEntity.noContent();
    }


}
