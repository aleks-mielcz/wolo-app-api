package pl.pjwstk.woloappapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.GlobalMapper;
import pl.pjwstk.woloappapi.model.Category;
import pl.pjwstk.woloappapi.model.dto.CategoryDTO;
import pl.pjwstk.woloappapi.service.CategoryService;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final GlobalMapper globalMapper;

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = globalMapper.categoryListToCategoryDTOList(categories);
        return new ResponseEntity<>(categoryDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        CategoryDTO categoryDTO = globalMapper.categoryToCategoryDTO(category);
        return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = globalMapper.categoryDTOToCategory(categoryDTO);
        categoryService.createCategory(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<HttpStatus> editCategory(@PathVariable Long id, @RequestBody CategoryDTO updatedCategoryDTO) {
        Category updatedCategory = globalMapper.categoryDTOToCategory(updatedCategoryDTO);
        categoryService.editCategory(id, updatedCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
