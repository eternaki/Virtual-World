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
public class Antylopa extends Animal{
    Antylopa(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Antylopa";
        this.inicjatywa = 4;
        this.sila =4;
        this.zasieg=2;
    }
    @Override
    protected void rozmnazanie(){
        Pozycja nowa = world.losoweWolneOtocz(this.getPozycja(), 1);
        if(nowa.czyRowne(this.getPozycja())) return;
        world.dodajOrganizm(nowa, this.getNazwa());
    }

    @Override
    public Color getColor() {
        return Color.pink;
    }
}
