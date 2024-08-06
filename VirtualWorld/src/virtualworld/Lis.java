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
public class Lis extends Animal{
    Lis(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Lis";
        this.inicjatywa = 7;
        this.sila =3;
    }
    
    @Override
    public void akcja(){        
        this.setWiek(this.getWiek()+1);
         this.mozeRuszac = true;        
            Pozycja pos = world.losoweOtocz(this.getPozycja(), this.getZasieg());
            if(this.world.znajdzPozycje(pos)!=null){
                if(this.getSila()<this.world.znajdzPozycje(pos).getSila()) return;
                ruch(pos);
            }
            ruch(pos);
    }
    
    @Override
    public Color getColor() {
        return Color.ORANGE;
    }
}
