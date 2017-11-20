/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.controllers;

import com.pevi.core.models.dto.AnonymousOrder;
import com.pevi.core.models.entity.Orders;
import com.pevi.core.services.OrderService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RequestMapping("api/orders")
@RestController
@Api(value = "Food Order Services API", description = " API")
public class OrderController {

    @Autowired
    private OrderService ordersvc;

    @PostMapping("saveOrder")
    public ResponseEntity saveOrder(@RequestBody AnonymousOrder order) {
        ordersvc.saveOrder(order);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("getOrders/{pageNo}")
    public ResponseEntity getOrders(@PathVariable("pageNo") String pageNo) {
        List<Orders> ords = ordersvc.getOrders(pageNo);
        return new ResponseEntity<List<Orders>>(ords, HttpStatus.OK);
    }

    @GetMapping("modifyOrder")
    public ResponseEntity modifyOrder(@RequestBody Orders ord) {
        Orders o = ordersvc.modifyOrder(ord);
        return new ResponseEntity<Orders>(o, HttpStatus.OK);
    }
    
    @DeleteMapping("deleteOrder/{orderId}")
    public ResponseEntity deleteorder(@PathVariable("orderId")String orderId){
        ordersvc.deleteOrder(orderId);
        return new ResponseEntity(HttpStatus.OK);
    }

}
