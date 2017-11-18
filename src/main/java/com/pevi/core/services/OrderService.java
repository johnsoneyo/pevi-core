/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.models.dto.AnonymousOrder;
import com.pevi.core.models.dto.OrderDTO;
import com.pevi.core.models.entity.Customers;
import com.pevi.core.models.entity.Orders;
import com.pevi.core.repository.CustomerRepository;
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

    public void saveOrder(AnonymousOrder ord) {

        Customers cus = crepo.findByEmail(ord.getEmail());
        List<OrderDTO> asList = Arrays.asList(ord.getOrders());

        asList.forEach(o -> {
            Orders or = new Orders();
            or.setProductId(prepo.findById(o.getProductId()));
            or.setPaid(Boolean.FALSE);
            or.setCustomerId(cus != null ? cus : newCustomer(ord));
            or.setTimeCreated(new Date());
            repo.save(or);
        });

    }

    public List<Orders> getOrders(String pageNo) {
        return repo.getOrders(Integer.parseInt(pageNo));
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

}
