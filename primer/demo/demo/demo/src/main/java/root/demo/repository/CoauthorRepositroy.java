package root.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import root.demo.model.Coauthor;

@Repository
public interface CoauthorRepositroy extends JpaRepository<Coauthor, Long> {
}
