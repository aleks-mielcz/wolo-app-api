package pl.pjwstk.woloappapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.woloappapi.model.Category;
import pl.pjwstk.woloappapi.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "admin/dashboard";
    }
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories")
    public String showCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin/categories";
    }

    @GetMapping("categories/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/addCategory";
    }

    @PostMapping("categories/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.createCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("categories/delete/{categoryId}")
    public String deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return "redirect:/admin/categories";
    }
}