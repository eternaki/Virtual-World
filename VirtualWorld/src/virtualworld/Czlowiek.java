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
public class Czlowiek extends Animal{    

    private int moveDirection=0;
    
    
    Czlowiek(Pozycja pos, World world) {
        super(pos, world);
        this.nazwa="Czlowiek";
        this.inicjatywa = 4;
        this.sila =5;
    }
    
    private int resetMocy = 10;
    public int getResetMocy() {return this.resetMocy;}
    
    private int superPower = 5;
    public int getSuperPower() {return this.superPower;}
    
    private boolean mocDostepna = true;
    public boolean getMocDostepna() {return this.mocDostepna;}
    
    private boolean mocAktywna = false;
    public boolean getMocAktywna() {return this.mocAktywna;}
    
    public void ustawMoc(int reset, int sup, boolean dost, boolean akt){
        this.resetMocy = reset;
        this.superPower = sup;
        this.mocDostepna = dost;
        this.mocAktywna = akt;        
    }
    
    public void aktywujMoc(){
        if(mocDostepna) {
            Pozycja pos = world.losoweOtocz(this.getPozycja(), this.getZasieg());
            if(this.world.znajdzPozycje(pos)!=null){
                if(this.getSila()<this.world.znajdzPozycje(pos).getSila()) return;
                ruch(pos);
            }
            ruch(pos);
            mocAktywna = true;
            mocDostepna = false;
            resetMocy=10;
            superPower=5;
        }
    }
    public void ladowanieMocy(){
        if(!mocDostepna && mocAktywna) {
            resetMocy--;
            this.setSila(this.getSila()-1);
        }
        if(resetMocy==0 && superPower>0) {
            mocAktywna = false;
            superPower--;
        }
        if(superPower == 0) {
            mocDostepna=true;            
            resetMocy=10;
            superPower=5;
        }        
    }
    
    @Override
    public void akcja(){
        this.setWiek(this.getWiek()+1);
        this.mozeRuszac = true;
        this.ladowanieMocy();        
        switch(this.world.actionKey){
            case 1: // lewo
                this.ruchLewo();
                break;
            case 2: //prawo
                this.ruchPrawo();
                break;
            case 3: //gora
                this.ruchGora();
                break;
            case 4: //dol
                this.ruchDol();
                break;
            case 5:               
                this.aktywujMoc();                
                this.world.actionKey =0;
                break;
            default: break;
        }
        if(mocAktywna) this.world.komunikat ="Supermoc aktywna jescze przez " + this.resetMocy + " tur. Sila: " + this.getSila();
        else if (mocDostepna)this.world.komunikat ="Aby aktywowac supermoc wcisnij spacje";
        else this.world.komunikat ="Supermoc bedzie aktywna za " + this.superPower + " tur.";
    }
    
    @Override
    public Color getColor() {
        return Color.white;
    }
}
