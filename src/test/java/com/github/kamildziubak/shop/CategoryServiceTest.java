package com.github.kamildziubak.shop;

import com.github.kamildziubak.shop.backend.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {
    CategoryService categoryService;

    @Autowired
    public CategoryServiceTest(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Test
    public void getCategoriesTreeTest()
    {
        System.out.println(categoryService.getCategoriesTree());
    }
}
