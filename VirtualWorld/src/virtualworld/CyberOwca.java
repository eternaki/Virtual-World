/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;

import java.awt.Color;
import static java.lang.Math.pow;


/**
 *
 * @author Lohachov Danylo
 */
public class CyberOwca extends Animal{
    CyberOwca(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="CyberOwca";
        this.inicjatywa = 4;
        this.sila =11;
    }
    
    
    @Override
    public void akcja(){
        this.mozeRuszac = true;
        this.setWiek(this.getWiek()+1);
        Organizm org = this.szukajNajblizszegoBarszczu();
        if(org !=null){
            Pozycja nowaPozycja = new Pozycja(0,0);
            //kierunek na barszcz
            
            //idziemy w lewo-gora
            if(org.getPozycja().getX() - this.getPozycja().getX() < 0 && org.getPozycja().getY() - this.getPozycja().getY() < 0 ) {
               this.ruchLewoGora();
            }
            //idziemy w lewo
            else if(org.getPozycja().getX() - this.getPozycja().getX() <0 && org.getPozycja().getY() - this.getPozycja().getY() == 0 ) {
               this.ruchLewo();
            }
            //idziemy w lewo-dol
            else if(org.getPozycja().getX() - this.getPozycja().getX() <0 && org.getPozycja().getY() - this.getPozycja().getY() > 0 ) {
                this.ruchLewoDol();
            }
            //idziemy w prawo-gora
            else if(org.getPozycja().getX() - this.getPozycja().getX() >0 && org.getPozycja().getY() - this.getPozycja().getY() < 0 ) {
                this.ruchPrawoGora();
            }
            //idziemy w prawo
            else if(org.getPozycja().getX() - this.getPozycja().getX() >0 && org.getPozycja().getY() - this.getPozycja().getY() == 0 ) {
                this.ruchPrawo();
            }
            //idziemy w prawo-dol
            else if(org.getPozycja().getX() - this.getPozycja().getX() >0 && org.getPozycja().getY() - this.getPozycja().getY() > 0 ) {
                this.ruchPrawoDol();
            }
            //idziemy w dol
            else if(org.getPozycja().getX() - this.getPozycja().getX() == 0 && org.getPozycja().getY() - this.getPozycja().getY() > 0 ) {
                this.ruchDol();
            }
            //idziemy w gore
            else if(org.getPozycja().getX() - this.getPozycja().getX() == 0 && org.getPozycja().getY() - this.getPozycja().getY() < 0 ) {
                this.ruchGora();
            } 
            return;
        } 
        ruch(world.losoweOtocz(this.getPozycja(), this.getZasieg()));
          
    }
    
    public Organizm szukajNajblizszegoBarszczu(){
        Organizm org = null;
        Organizm helporg;
        double distance = pow(2, this.world.getRozmiar().getX())+pow(2, this.world.getRozmiar().getY());
        double helpdistance;
        for(int i=0; i<this.world.getIleOrganizmow(); i++){
            if(this.world.organizmy[i] instanceof BarszczS){
                //liczymy odleglosc
                helporg = this.world.organizmy[i];
                helpdistance = pow(2, (this.getPozycja().getX()-helporg.getPozycja().getX()))+ pow(2, (this.getPozycja().getY()-helporg.getPozycja().getY()));
                if(distance > helpdistance) {
                    distance = helpdistance;
                    org = helporg;
                }
            }
        }
        return org;
    }
    
    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }

    private double pov(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
