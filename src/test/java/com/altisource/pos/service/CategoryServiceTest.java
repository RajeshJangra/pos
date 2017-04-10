package com.altisource.pos.service;

import com.altisource.pos.domain.Category;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.CategoryRepository;
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
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateCategorySuccessful() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryRepository.exists(expected.getId())).thenReturn(false);
        when(categoryRepository.save(expected)).thenReturn(expected);
        Category returned = categoryService.createCategory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateCategoryAndCategoryAlreadyExists() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryRepository.exists(expected.getId())).thenReturn(true);
        categoryService.createCategory(expected);
    }

    @Test
    public void testUpdateCategorySuccessful() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryRepository.exists(expected.getId())).thenReturn(true);
        when(categoryRepository.save(expected)).thenReturn(expected);
        Category returned = categoryService.updateCategory(expected);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testUpdateCategoryAndCategoryAlreadyExists() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryRepository.exists(expected.getId())).thenReturn(false);
        categoryService.updateCategory(expected);
    }

    @Test
    public void testGetCategorySuccessful() throws Exception {
        long categoryId = 1L;
        Category expected = getCategory(categoryId);
        when(categoryRepository.findOne(expected.getId())).thenReturn(expected);
        Category returned = categoryService.getCategory(expected.getId());
        assertEquals(expected, returned);
    }

    private Category getCategory(long categoryId) {
        Category applianceCareCategory = new Category(10, "Appliance", "Appliance Category");
        applianceCareCategory.setId(categoryId);
        return applianceCareCategory;
    }
}
