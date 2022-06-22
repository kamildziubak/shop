package com.github.kamildziubak.shop.backend.api;

import com.github.kamildziubak.shop.backend.modules.dbModules.Category;
import com.github.kamildziubak.shop.backend.modules.nodDbModules.Node;
import com.github.kamildziubak.shop.backend.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    private final CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ArrayList<Node<Category>> getCategories(){
        return categoryService.getCategoriesTree();
    }
}
