package apiario.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String menu() {
	return "menu";
    }

    @GetMapping("/apiariz")
    public String inviaApiario() {
	return "apiario";
    }

    @GetMapping("/arniez")
    public String inviaArnia() {
	return "arnia";
    }

    @GetMapping("/produzioniz")
    public String inviaProduzione() {
	return "produzione";
    }

    @GetMapping("/trattamentiz")
    public String inviaTrattamento() {
	return "trattamento";
    }

    @GetMapping("/statistichez")
    public String inviaStatistiche() {
	return "statistiche";
    }
}
