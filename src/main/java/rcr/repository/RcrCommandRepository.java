package rcr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rcr.model.entity.RcrCommand;

import java.util.List;

public interface RcrCommandRepository extends JpaRepository<RcrCommand, Long> {

    public List<RcrCommand> findAllByOrderByIdAsc();
}