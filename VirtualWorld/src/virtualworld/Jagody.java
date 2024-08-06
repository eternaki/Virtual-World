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
public class Jagody extends Roslina{
    Jagody(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Jagody";        
        this.sila =99;
    }
    
    @Override
    public boolean specjalnaKolizja(Organizm other){
        other.zabij();
        return true;
    }
    
    
    @Override
    public Color getColor() {
        return Color.MAGENTA;
    }
}
