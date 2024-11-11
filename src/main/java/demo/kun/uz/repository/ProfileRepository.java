package demo.kun.uz.repository;

import demo.kun.uz.entity.ProfileEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByIdAndVisibleTrue(Long id);
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

}
