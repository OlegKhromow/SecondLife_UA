package com.secondlife_ua.repositories;

import com.secondlife_ua.entities.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(value = false)
class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void testSaveCategoryObject() {
        Category category = new Category("хліб");
        Category savedCategory = categoryRepository.save(category);
        assertNotNull(savedCategory);
        assertTrue(savedCategory.getId() > 0);
    }

    @Test
    public void testFindAllCategories() {
        Iterable<Category> categories = categoryRepository.findAll();
        assertNotNull(categories);
    }

    @Test
    public void testFindCategoryByName() {
        categoryRepository.save(new Category("хлібобулочні вироби"));
        List<Category> category = categoryRepository.findByNameLikeIgnoreCase("хліб");
        for (Category category1 : category) {
            System.out.println(category1.getId() + " " + category1.getName());
        }
        assertEquals(2, category.size());
    }
}