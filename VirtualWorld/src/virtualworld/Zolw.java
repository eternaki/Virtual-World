/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Lohachov Danylo
 */
public class Zolw extends Animal{
    Zolw(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Zolw";
        this.inicjatywa = 1;
        this.sila =2;
    }
    
    @Override
    public void akcja(){
        this.mozeRuszac = true;
        Random losowa = new Random();
        if(losowa.nextInt(4)==0) 
            ruch(world.losoweOtocz(this.getPozycja(), this.getZasieg()));
        this.setWiek(this.getWiek()+1);
        
    }
    
    @Override
    public Color getColor() {
        return Color.CYAN;
    }
}

