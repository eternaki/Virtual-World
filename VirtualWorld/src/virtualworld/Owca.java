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
public class Owca extends Animal{
    Owca(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Owca";
        this.inicjatywa = 4;
        this.sila =4;
    }
    
    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }
}
