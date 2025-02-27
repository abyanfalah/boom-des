package id.asqi.idesa.bumdes.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AppController {
	@GetMapping
	public String test() {
		return "MELEDACC \uD83C\uDF49 \uD83C\uDF49 \uD83C\uDF49";
	}

}