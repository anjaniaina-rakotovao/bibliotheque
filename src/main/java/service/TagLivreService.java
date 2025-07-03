package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import entities.TagLivreEntity;
import repository.TagLivreRepository;

public class TagLivreService {

    private TagLivreRepository tagLivreRepository;

    @Autowired
    public void setTagLivreRepository(TagLivreRepository tagLivreRepository) {
        this.tagLivreRepository = tagLivreRepository;
    }

    public List<TagLivreEntity> findAll() {
        return tagLivreRepository.findAll();
    }

    public TagLivreEntity save(TagLivreEntity tagLivre) {
        return tagLivreRepository.save(tagLivre);
    }

    public void delete(Integer id) {
        tagLivreRepository.deleteById(id);
    }

    // public List<TagLivreEntity> findByLivre(Integer idLivre) {
    //     return tagLivreRepository.findByLivre_IdLivre(idLivre);
    // }

    // public List<TagLivreEntity> findByTag(Integer idTag) {
    //     return tagLivreRepository.findByTag_IdTag(idTag);
    // }
}