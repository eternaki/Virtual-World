/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package virtualworld;
import java.awt.Color;
import java.io.Serializable;


/**
 *
 * @author Lohachov Danylo
 */
public abstract class Organizm implements Serializable{

    int moveDirection;
    
    public Organizm(Pozycja pos, World world){
        this.wiek = 0;
        this.pozycja = pos;
        this.world = world;
    }

    protected String nazwa;
    
    public String getNazwa(){
        return this.nazwa;
    }
    
    protected int sila;
    
    public int getSila(){
        return this.sila;
    }
    
    public void setSila(int s){
        this.sila = s;
    }
    
    protected int inicjatywa;
    
    public int getInicjatywa(){
        return this.inicjatywa;
    }
    
    protected int zasieg;
    
    public int getZasieg(){
        return this.zasieg;
    }
    
    protected boolean czyZyje = true;

    public void zabij(){
        this.czyZyje = false;
    }    
    
    public boolean czyZywy(){
        return this.czyZyje;
    }
    
    protected int wiek;
    
    public int getWiek(){
        return this.wiek;
    }
    
    public void setWiek(int n){
        this.wiek =n; 
    }
    
    protected Pozycja pozycja;
    
    public Pozycja getPozycja(){
        return pozycja;
    }
    
    protected World world;    
    
    protected void rozmnazanie(){
        Pozycja nowa = world.losoweWolneOtocz(this.getPozycja(), this.getZasieg());
        if(nowa.czyRowne(this.getPozycja())) return;
        world.dodajOrganizm(nowa, this.getNazwa());
    }

    protected boolean mozeRuszac = true;    
    
    public void kolizja(Organizm atakujacy, Organizm atakowany){
        if(atakujacy.getNazwa().equals(atakowany.getNazwa())){
            this.rozmnazanie();
            atakujacy.mozeRuszac = false;
            atakowany.mozeRuszac = false;
            world.AddLog(atakowany.getNazwa() + "Skresla z listy" + atakowany.getNazwa());
            return;
        }
        if(!this.specjalnaKolizja(atakowany))return;
        if(!atakowany.specjalnaKolizja(this))return;
        
        if(atakujacy.getSila()>=atakowany.getSila()){
            atakowany.zabij();
            return;
        }
        atakujacy.zabij();
        return;
    }
    
    public boolean specjalnaKolizja(Organizm other){
        return true;
    }
    
    public void akcja(){}
    public abstract Color getColor(); 
   
    
}
