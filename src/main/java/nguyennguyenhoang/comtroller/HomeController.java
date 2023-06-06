package nguyennguyenhoang.comtroller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
	@GetMapping("/home")
    public String greeting() {
        return "login";
    }
}
