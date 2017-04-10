package com.altisource.pos.controller;

import com.altisource.pos.domain.Category;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * BillService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 10, 2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;
    @Mock
    private CategoryService categoryService;

    @Test
    public void testCreateCategorySuccessful() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryService.createCategory(expected)).thenReturn(expected);
        Category returned = categoryController.createCategory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateCategoryAndCategoryAlreadyExists() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryService.createCategory(expected)).thenThrow(new PosApplicationException("Category: " + expected.getId() + " already exists"));
        categoryController.createCategory(expected);
    }

    @Test
    public void testUpdateCategorySuccessful() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryService.updateCategory(expected)).thenReturn(expected);
        Category returned = categoryController.updateCategory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testUpdateCategoryAndCategoryAlreadyExists() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryService.updateCategory(expected)).thenThrow(new PosApplicationException("Category: " + expected.getId() + " already exists"));
        categoryController.updateCategory(expected);
    }

    @Test
    public void testGetCategorySuccessful() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryService.getCategory(expected.getId())).thenReturn(expected);
        Category returned = categoryController.getCategory(expected.getId());
        assertEquals(expected, returned);
    }

    private Category getCategory(long categoryId) {
        Category applianceCareCategory = new Category(10, "Appliance", "Appliance Category");
        applianceCareCategory.setId(categoryId);
        return applianceCareCategory;
    }
}
