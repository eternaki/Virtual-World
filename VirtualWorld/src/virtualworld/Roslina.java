/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.util.Random;

/**
 *
 * @author Lohachov Danylo
 */
public abstract class Roslina extends Organizm{
    public Roslina(Pozycja pos, World world) {
        super(pos, world);
        this.inicjatywa = 0;
        this.zasieg=1;
    }
    
    protected static final int PRAWDOPODOBIENSTWOZASIEWU = 30;
    
    @Override
    public void akcja(){
        this.setWiek(this.getWiek()+1);
        Random losowa = new Random();
        if(losowa.nextInt(PRAWDOPODOBIENSTWOZASIEWU)==0)this.rozmnazanie();
    }
    
}
