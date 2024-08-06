/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.awt.Color;

/**
 *
 * @author Lohachov Danylo
 */
public class Wilk extends Animal {

    Wilk(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Wilk";
        this.inicjatywa = 5;
        this.sila =9;
        
    }    
   @Override
    public java.awt.Color getColor() {
        return Color.GRAY;
    }
}