package id.asqi.idesa.bumdes.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AppController {
	@GetMapping
	public String test() {
		return "MELEDACC \uD83C\uDF49 \uD83C\uDF49 \uD83C\uDF49";
	}

	/*don't get this shit. will figure it out later.*/
	@PostMapping("asdfasdf")
	public Object formData (@ModelAttribute Map<String, String> req) {
		return "asdf";
	}

	@GetMapping("asdfasdf")
	public Object formDataget (Map<String, String> req) {
		return "asdf";

	}
}