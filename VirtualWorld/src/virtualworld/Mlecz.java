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
public class Mlecz extends Roslina{
    Mlecz(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Mlecz";        
        this.sila =0;
    }
    
    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
    
    @Override
    public boolean specjalnaKolizja(Organizm other){
        return false;
    }
    
    @Override
    public void akcja(){
        this.mozeRuszac = true;
        this.setWiek(this.getWiek()+1);
        Random losowa = new Random();
        for(int i=0; i<3; i++) if(losowa.nextInt(PRAWDOPODOBIENSTWOZASIEWU)==0)this.rozmnazanie();
    }
    
}
