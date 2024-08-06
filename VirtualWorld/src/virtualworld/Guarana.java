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
public class Guarana extends Roslina{
    Guarana(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Guarana";        
        this.sila =0;
    }
    
    @Override
    public boolean specjalnaKolizja(Organizm other){
        other.setSila(other.getSila()+3);
        return true;
    }
    
    
    @Override
    public Color getColor() {
        return Color.blue;
    }
}
