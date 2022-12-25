package dev.evilasio.lukedirector.controller.api.v2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/luke-movies")
public class DesafioDoisController {

    @GetMapping()
    public ResponseEntity<?> getAllMovies() {
        return null;
    }

    @PostMapping()
    public ResponseEntity<?> saveLukeMovies() {
        return null;
    }

    @GetMapping("/seed-movies")
    public ResponseEntity<?> seedLukeMovies() {
        return null;
    }

    @DeleteMapping("/clear-all-movies")
    public ResponseEntity<?> getLukeMovies() {
        return null;
    }

}
