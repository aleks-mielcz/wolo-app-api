package pl.pjwstk.woloappapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pjwstk.woloappapi.model.Category;
import pl.pjwstk.woloappapi.repository.CategoryRepository;
import pl.pjwstk.woloappapi.utils.NotFoundException;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category id not found!"));
    }

    public void createCategory(Category Category) {
        categoryRepository.save(Category);
    }

    public void editCategory(Long id, Category updatedCategory) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category with ID " + id + " does not exist");
        }

        Category existingCategory = categoryRepository.findById(id).orElseThrow();
        existingCategory.setName(updatedCategory.getName());

        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalArgumentException("Category with ID " + id + " does not exist");
        }
        categoryRepository.deleteById(id);
    }


}