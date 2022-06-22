package com.github.kamildziubak.shop.backend.modules.nodDbModules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Node<T>{
    private T data;
    private List<Node<T>> subcategories = new ArrayList<>();
    @JsonIgnore
    private Node<T> master;

    public Node(T data)
    {
        this.data = data;
    }

    public void addChild(Node<T> subcategory)
    {
        subcategory.setMaster(this);
        subcategories.add(subcategory);
    }

    public void setMaster(Node<T> node)
    {
        master = node;
    }

    public T getData()
    {
        return data;
    }

    public List<Node<T>> getSubcategories() {
        return subcategories;
    }

    public Node getMaster() {
        return master;
    }

    @Override
    public String toString()
    {
        try {
            return data.toString() + " subcategories: " + subcategories;
        }
        catch(java.lang.NullPointerException e){
            return data.toString();
        }
    }
}
