package pl.pjwstk.woloappapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjwstk.woloappapi.model.Category;
import pl.pjwstk.woloappapi.repository.CategoryRepository;
import pl.pjwstk.woloappapi.utils.EventNotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException("Category id not found!"));
    }

    public void createCategory(Category Category) {
        categoryRepository.save(Category);
    }

    public Category updateCategory(Category Category) {
        if (!categoryRepository.existsById(Category.getId())) {
            throw new IllegalArgumentException("Category with ID " + Category.getId() + " does not exist");
        }
        return categoryRepository.save(Category);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist");
        }
        categoryRepository.deleteById(id);
    }


}