/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.order;

import java.io.Serializable;
import java.util.List;

import za.ac.tut.dish.Dish;

/**
 *
 * @author hp
 */
public class Order implements Serializable{

    private int orderNo;
    private List<Dish> dishes;

    public Order() {
    }

    public Order(int orderNo, List<Dish> dishes) {
        this.orderNo = orderNo;
        this.dishes = dishes;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

}
