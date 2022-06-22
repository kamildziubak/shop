package com.github.kamildziubak.shop.backend.services;

import com.github.kamildziubak.shop.backend.dao.CategoryRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.Category;
import com.github.kamildziubak.shop.backend.modules.nodDbModules.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ArrayList<Node<Category>> getCategoriesTree()
    {
        ArrayList<Category> categories = (ArrayList<Category>) categoryRepository.findAll();
        ArrayList<Node<Category>> categoriesTree = new ArrayList<>();

        for(int i=0; i<categories.size(); i++){
            categoriesTree.add(new Node<>(categories.get(i)));
        }

        for(int i=0; i<categoriesTree.size(); i++) {
            Node<Category> category = categoriesTree.get(i);
            try{
                Node<Category> master = categoriesTree.get(category.getData().getMasterId()-1);
                master.addChild(category);
            }
            catch (java.lang.NullPointerException e) {
            }
        }
        return categoriesTree;
    }

    public Node<Category> getCategoryById(int ctgId)
    {
        return getCategoriesTree().get(ctgId);
    }

    public int getLastId(){
        List<Category> categories = categoryRepository.findAll();
        Category lastCategory = categories.get(categories.size()-1);
        return lastCategory.getCtgId();
    }
}
