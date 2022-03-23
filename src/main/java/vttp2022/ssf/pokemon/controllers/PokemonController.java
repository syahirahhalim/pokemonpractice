package vttp2022.ssf.pokemon.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.pokemon.models.Pokemon;
import vttp2022.ssf.pokemon.services.PokemonService;

@Controller
@RequestMapping("/search")
public class PokemonController {
    @Autowired
    PokemonService pokeSvc;

    @GetMapping
    public String getPokemon(@RequestParam String pokemon_name, Model model) {
        Optional<Pokemon> p = pokeSvc.findPokemon(pokemon_name.toLowerCase());

        if (p.isEmpty()) {
            model.addAttribute("msg", "Pokemon not found!");
            return "error";
        }
        model.addAttribute("search", pokemon_name.toUpperCase());
        model.addAttribute("photos", p.get().getPhotos());
        return "searchResults";
    }
}