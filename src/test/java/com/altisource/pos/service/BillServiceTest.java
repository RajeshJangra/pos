package com.altisource.pos.service;

import com.altisource.pos.domain.*;
import com.altisource.pos.exception.PosApplicationException;
import com.altisource.pos.repository.BillRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.altisource.pos.util.Constant.PAGE_SIZE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * BillService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 10, 2017</pre>
 */
@RunWith(MockitoJUnitRunner.class)
public class BillServiceTest {

    @InjectMocks
    private BillService billService;
    @Mock
    private BillRepository billRepository;
    @Mock
    private CartService cartService;


    @Test
    public void testGetBillsByTotalBillAmountAsc() throws Exception {
        Page<Bill> expected = new PageImpl<>(getBills());
        String totalBillAmount = "totalBillAmount";
        String asc = "ASC";
        when(billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(null, null, 1200d, new PageRequest(0, PAGE_SIZE, new Sort(Sort.Direction.fromString(asc), totalBillAmount)))).thenReturn(expected);
        Page<Bill> returned = billService.getBills(null, null, 1200d, 0, totalBillAmount, asc);
        assertEquals(expected, returned);
    }

    @Test
    public void testGetBillsByBillDateAsc() throws Exception {
        Page<Bill> expected = new PageImpl<>(getBills());
        String billDateString = "billDate";
        String asc = "ASC";
        Date billDate = new Date();
        when(billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(billDate, null, null, new PageRequest(0, PAGE_SIZE, new Sort(Sort.Direction.fromString(asc), billDateString)))).thenReturn(expected);
        Page<Bill> returned = billService.getBills(billDate, null, null, 0, billDateString, asc);
        assertEquals(expected, returned);
    }

    @Test
    public void testGetBillsByLocationCodeAsc() throws Exception {
        Page<Bill> expected = new PageImpl<>(getBills());
        String billDateString = "locationCode";
        String asc = "ASC";
        Long locationCode = 1L;
        when(billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(null, locationCode, null, new PageRequest(0, PAGE_SIZE, new Sort(Sort.Direction.fromString(asc), billDateString)))).thenReturn(expected);
        Page<Bill> returned = billService.getBills(null, locationCode, null, 0, billDateString, asc);
        assertEquals(expected, returned);
    }

    @Test
    public void testGetBillsByTotalBillAmountDesc() throws Exception {
        Page<Bill> expected = new PageImpl<>(getBills());
        String totalBillAmount = "totalBillAmount";
        String asc = "DESC";
        when(billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(null, null, 1200d, new PageRequest(0, PAGE_SIZE, new Sort(Sort.Direction.fromString(asc), totalBillAmount)))).thenReturn(expected);
        Page<Bill> returned = billService.getBills(null, null, 1200d, 0, totalBillAmount, asc);
        assertEquals(expected, returned);
    }

    @Test
    public void testGetBillsByBillDateDesc() throws Exception {
        Page<Bill> expected = new PageImpl<>(getBills());
        String billDateString = "billDate";
        String asc = "DESC";
        Date billDate = new Date();
        when(billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(billDate, null, null, new PageRequest(0, PAGE_SIZE, new Sort(Sort.Direction.fromString(asc), billDateString)))).thenReturn(expected);
        Page<Bill> returned = billService.getBills(billDate, null, null, 0, billDateString, asc);
        assertEquals(expected, returned);
    }

    @Test
    public void testGetBillsByLocationCodeDesc() throws Exception {
        Page<Bill> expected = new PageImpl<>(getBills());
        String billDateString = "locationCode";
        String asc = "DESC";
        Long locationCode = 1L;
        when(billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(null, locationCode, null, new PageRequest(0, PAGE_SIZE, new Sort(Sort.Direction.fromString(asc), billDateString)))).thenReturn(expected);
        Page<Bill> returned = billService.getBills(null, locationCode, null, 0, billDateString, asc);
        assertEquals(expected, returned);
    }

    @Test(expected = PosApplicationException.class)
    public void testGetBillsWrongFieldName() throws Exception {
        Page<Bill> expected = new PageImpl<>(getBills());
        String billDateString = "wrongFieldName";
        String asc = "DESC";
        Long locationCode = 1L;
        when(billRepository.findByBillDateOrLocationCodeOrTotalBillAmount(null, locationCode, null, new PageRequest(0, PAGE_SIZE, new Sort(Sort.Direction.fromString(asc), billDateString)))).thenReturn(expected);
        Page<Bill> returned = billService.getBills(null, locationCode, null, 0, billDateString, asc);
    }

    @Test(expected = PosApplicationException.class)
    public void testCreateBillCartDoesNotExist() throws Exception {
        long cartId = 1;
        when(cartService.getCart(cartId)).thenReturn(null);
        billService.createBill(cartId);
    }

    @Test
    public void testCreateBillCartExists() throws Exception {
        long cartId = 1;
        Cart cart = getCart(cartId);
        Bill expected = new Bill.Builder().cart(cart).build();
        when(cartService.getCart(cartId)).thenReturn(cart);
        when(billRepository.save(any(Bill.class))).thenReturn(expected);
        Bill returned = billService.createBill(cartId);
        assertEquals(expected, returned);
    }

    private List<Bill> getBills() {
        long cartId1 = 1;
        Cart cart1 = getCart(cartId1);
        Bill bill1 = new Bill.Builder().cart(cart1).build();
        long cartId2 = 1;
        Cart cart2 = getCart(cartId2);
        Bill bill2 = new Bill.Builder().cart(cart2).build();

        List<Bill> bills = new ArrayList<>();
        bills.add(bill1);
        bills.add(bill2);

        return bills;
    }

    private Cart getCart(long cartId) {
        Territory bengaluruTerritory = new Territory(10, "Bengaluru", "Bengaluru Territory");
        Category applianceCareCategory = new Category(10, "Appliance", "Appliance Category");
        Product sandwichMaker = new Product("Sandwich Maker", "Black and Decker Sandwich Maker", 1000, applianceCareCategory);
        ProductOrder sandwichMakerProductOrder = new ProductOrder(sandwichMaker, 2);
        List<ProductOrder> productOrderList = new ArrayList<>();
        productOrderList.add(sandwichMakerProductOrder);

        Cart cart = new Cart(bengaluruTerritory, productOrderList);
        cart.setId(cartId);
        return cart;
    }
}
