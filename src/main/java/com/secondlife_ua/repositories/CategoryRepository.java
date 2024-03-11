package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("select c from Category c where upper(c.name) like concat('%', upper(?1), '%')")
    List<Category> findByNameLikeIgnoreCase(String name);
}