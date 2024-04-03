package pis.projekt.helloapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class HelloController {

    private final LanguageRepository languageRepository;
    private final GreetingRepository greetingRepository;

    @Autowired
    public HelloController(LanguageRepository languageRepository, GreetingRepository greetingRepository) {
        this.languageRepository = languageRepository;
        this.greetingRepository = greetingRepository;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "en") String language) {

        Language lang = languageRepository.findByCode(language);

        List<Greeting> greetings = greetingRepository.findByLanguage(lang);
        return greetings.get(0).getGreetingText();
    }

    @GetMapping("/languages")
    public Page<Language> languages(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        return languageRepository.findAll(pageRequest);
    }
}

