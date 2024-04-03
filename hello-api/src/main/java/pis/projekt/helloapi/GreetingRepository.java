package pis.projekt.helloapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    @Query("SELECT g FROM Greeting g WHERE g.language = ?1")
    List<Greeting> findByLanguage(Language language);
}
