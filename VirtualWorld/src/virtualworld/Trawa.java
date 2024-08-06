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
public class Trawa extends Roslina{
    Trawa(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Trawa";        
        this.sila =0;
    }    
    
    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}
