package pis.projekt.helloapi;

import jakarta.persistence.*;

@Entity
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    private String greetingText;

    public Greeting() {
    }

    public Greeting(Language language, String greetingText) {
        this.language = language;
        this.greetingText = greetingText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }
}
