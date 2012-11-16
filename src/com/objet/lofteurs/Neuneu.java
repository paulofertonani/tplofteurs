/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

import java.util.Random;

/**
 *
 * @author paulo
 */
public abstract class Neuneu {
    protected int energie;
    protected int typeNeuneu;
    protected Loft monLoft;
    protected int[] position;
    protected String nomImage;
    protected boolean enReproduction;

    public Neuneu(int energie, int typeNeuneu, Loft monLoft, int[] position) {
        this.energie = energie;
        this.typeNeuneu = typeNeuneu;
        this.monLoft = monLoft;
        this.position = position;
        this.enReproduction = false;
    }

    public String getNomImage() {
        return nomImage;
    }
    
    /**
    * La methode retourne le valeur 'int' des neuneus:
    * 1 - erratique
    * 2 - voraces
    * 3 - canibales 
    * 4 - lapins
    *
    */
    public int getTypeNeuneu(){
        return this.typeNeuneu;
    }

    public int getEnergie(){
        return this.energie;
    }

    public void setEnergie(int par){
        this.energie = par;
    }
    
    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public boolean isEnReproduction() {
        return enReproduction;
    }

    public void setEnReproduction(boolean enReproduction) {
        this.enReproduction = enReproduction;
    }
    
    public void tuerNeuneu(){
        this.monLoft.c[this.position[0]][this.position[1]].setNeuneu(null);
        this.monLoft.listaneu.remove(this);
        
    }
    
    public void effacerNourriture(Nourriture n){
        this.monLoft.c[this.position[0]][this.position[1]].setNourriture(null);
        this.monLoft.listanou.remove(n);
    }
    
    public void deplacerParHazard(){
        Random randomGenerator = new Random();
        
        //je fait ça parce que si je faire testVide = this.position, en changeant 
        //testVide, je vais changer aussi this.position..........
        int[] testVide;
        int intermediate, intermediate2;
        testVide = new int[2];
        intermediate = this.position[0];
        intermediate2 = this.position[1];
        
        testVide[0] = intermediate;
        testVide[1] = intermediate2;
        
        
        switch (randomGenerator.nextInt(8)){
            case 0:
                if(this.position[0] != 0){
                    testVide[0] = this.position[0] - 1;
                }
                if(this.position[1] != 0){
                   testVide[1] = this.position[1] - 1; 
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            case 1:
                if(this.position[1] != 0){
                   testVide[1] = this.position[1] - 1; 
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            case 2:
                if(this.position[0] != this.monLoft.getTailleLoft() - 1){
                    testVide[0] = this.position[0] + 1;
                }
                if(this.position[1] != 0){
                   testVide[1] = this.position[1] - 1; 
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            case 3:
                if(this.position[0] != this.monLoft.getTailleLoft() - 1){
                    testVide[0] = this.position[0] + 1;
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            case 4:
                if(this.position[0] != this.monLoft.getTailleLoft() - 1){
                    testVide[0] = this.position[0] + 1;
                }
                if(this.position[1] != this.monLoft.getTailleLoft() - 1){
                   testVide[1] = this.position[1] + 1; 
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            case 5:
                if(this.position[1] != this.monLoft.getTailleLoft() - 1){
                   testVide[1] = this.position[1] + 1; 
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            case 6:
                if(this.position[0] != 0){
                    testVide[0] = this.position[0] - 1;
                }
                if(this.position[1] != this.monLoft.getTailleLoft() - 1){
                   testVide[1] = this.position[1] + 1; 
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            case 7:
                if(this.position[0] != 0){
                    testVide[0] = this.position[0] - 1;
                }
                if (this.monLoft.caseVide(testVide[0], testVide[1])){
                    this.position = testVide;
                    this.monLoft.remplacerNeuneuCase(intermediate, intermediate2, testVide[0], testVide[1], this);
                }
                
                break;
            default:
                System.out.println("fire in a hole !!");
                break;
        }
        
        this.energie = this.energie - 1;
    }

    public abstract void manger();

    public abstract void reproduire();

    public abstract void deplacer();
    
    public abstract boolean cannibale();

    
    
}
