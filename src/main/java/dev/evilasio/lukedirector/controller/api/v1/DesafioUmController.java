package dev.evilasio.lukedirector.controller.api.v1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/luke-movies")
public class DesafioUmController {

    @GetMapping()
    public ResponseEntity<?> getLukeMovies() {
        return null;
    }
}
