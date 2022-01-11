/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.disha;

import java.io.Serializable;
import za.ac.tut.dish.Dish;

/**
 *
 * @author hp
 */
public class DishA extends Dish implements Serializable{

    public DishA() {
    }

    public DishA(String name, double price, int qty) {
        super(name, price, qty);
    }



}
