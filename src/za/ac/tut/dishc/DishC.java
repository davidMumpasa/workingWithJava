/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.dishc;

import java.io.Serializable;
import za.ac.tut.dish.Dish;

/**
 *
 * @author hp
 */
public class DishC extends Dish implements Serializable{

    public DishC() {
    }

    public DishC(String name, double price, int qty) {
        super(name, price, qty);
    }


}
