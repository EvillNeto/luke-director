package dev.evilasio.lukedirector.domain.response.swapi;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SwapiPeopleResponse {

    private String name;
    private List<String> films;
}
