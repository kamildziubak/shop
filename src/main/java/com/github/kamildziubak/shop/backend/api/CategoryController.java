package com.github.kamildziubak.shop.backend.api;
import com.github.kamildziubak.shop.backend.dao.CategoryRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.Category;
import com.github.kamildziubak.shop.backend.modules.nodDbModules.Node;
import com.github.kamildziubak.shop.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public Node<Category> getCategory(@RequestParam("id") int ctgId){
        return categoryService.getCategoryById(ctgId-1);
    }

    @PutMapping
    public void editCategory(@RequestBody Category category){
        categoryRepository.save(category);
    }

    @PostMapping
    public void createCategory(@RequestBody Category category){
        category.setCtgId(categoryService.getLastId()+1);
        categoryRepository.save(category);
    }

}
