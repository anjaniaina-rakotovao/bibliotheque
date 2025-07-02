package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.TagEntity;
import repository.TagRepository;

public class TagService {

    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagEntity> findAll() {
        return tagRepository.findAll();
    }

    public TagEntity save(String tagName) {
        TagEntity tag = new TagEntity();
        tag.setTagName(tagName);
        return tagRepository.save(tag);
    }

    public void delete(Integer id) {
        tagRepository.deleteById(id);
    }
}
