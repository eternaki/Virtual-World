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
public class BarszczS extends Roslina{
    BarszczS(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="BarszczS";        
        this.sila =10;
    }
    
    @Override
    public void akcja(){
        this.mozeRuszac = true;
        this.setWiek(this.getWiek()+1);
        this.world.zabijAOE(this.getPozycja());
    }
    
    @Override
    public boolean specjalnaKolizja(Organizm other){
        if(!(other instanceof CyberOwca)) other.zabij();
        return true;
    }
    
    
    @Override
    public Color getColor() {
        return Color.red;
    }
    
    
}
