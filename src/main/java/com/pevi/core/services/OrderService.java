/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.constants.Constants;
import com.pevi.core.models.dto.AnonymousOrder;
import com.pevi.core.models.dto.OrderDTO;
import com.pevi.core.models.entity.Customers;
import com.pevi.core.models.entity.Invoice;
import com.pevi.core.models.entity.Orders;
import com.pevi.core.repository.CustomerRepository;
import com.pevi.core.repository.InvoiceRepository;
import com.pevi.core.repository.OrderRepository;
import com.pevi.core.repository.ProductRepository;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;
    @Autowired
    private CustomerRepository crepo;
    @Autowired
    private ProductRepository prepo;
    @Autowired
    private InvoiceRepository irepo;

    public Invoice saveOrder(AnonymousOrder ord) {

        List<Customers> cus = crepo.findByEmail(ord.getEmail());
        List<OrderDTO> asList = Arrays.asList(ord.getOrders());
        Invoice inv = new Invoice();
        inv.setPaid(Boolean.FALSE);
        inv.setAlias(ord.getEmail());
        inv = irepo.save(inv);

        for (OrderDTO o : asList) {
            Orders or = new Orders();
            or.setProductId(prepo.findById(o.getProductId()));
            or.setQuantity(o.getQuantity());
            or.setCustomerId(cus != null ? cus.get(0) : newCustomer(ord));
            or.setTimeCreated(new Date());
            or.setInvoiceId(inv);
            repo.save(or);
        }

        return inv;

    }

    public List<Orders> getOrders(String pageNo) {
        int res = (Integer.parseInt(pageNo) - 1);
        int offset = res != 0 ? res * (Constants.resultCount) + 1 : 0;
        return repo.getOrders(offset);
    }

    private Customers newCustomer(AnonymousOrder ord) {
        Customers c = new Customers();
        c.setFname(ord.getFname());
        c.setLname(ord.getLname());
        c.setEmail(ord.getEmail());
        c.setAddress(ord.getAddress());
        c.setPhone(ord.getPhone());
        return crepo.save(c);
    }

    public Orders modifyOrder(Orders ord) {
        return repo.save(ord);
    }

    public void deleteOrder(String orderId) {
        Orders findOne = this.repo.findOne(Integer.parseInt(orderId));
        this.repo.delete(findOne);
    }

    private void sendEmail(Customers cus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
