package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.TagEntity;

public interface TagRepository extends JpaRepository<TagEntity, Integer>{
    
}
