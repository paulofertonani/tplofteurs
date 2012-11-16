/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

/**
 *
 * @author paulo
 */
public class Case {
    /**
     * Definition des elements possibles dans une case
     * Le neuneu2 est seulement utilise en cas de reproduction
     * 
     * 
     *  */
    private Nourriture nourriture;
    protected Neuneu neuneu1;
    protected Neuneu neuneu2;

    public Case(Nourriture n) {
        this.nourriture = n;
        this.neuneu1 = null;
        this.neuneu2 = null;
    }

    public Neuneu getNeuneu() {
        return this.neuneu1;
    }
    public Neuneu getNeuneu2(){
        return this.neuneu2;
    }
    
    public int getEnergie1(){
        return this.neuneu1.getEnergie();
    }

    public void setNeuneu(Neuneu neuneu) {
        this.neuneu1 = neuneu;
    }
    
    public void setNeuneu2(Neuneu neuneu) {
        this.neuneu2 = neuneu;
    }
    
    public int getTypeNeuneu(){
        if(this.neuneu1 == null)
            return -1;
        return this.neuneu1.typeNeuneu;
    }

    public Nourriture getNourriture() {
        return nourriture;
    }

    public void setNourriture(Nourriture nourriture) {
        this.nourriture = nourriture;
    }
    
    public int getQuantiteNourriture(){
        return this.nourriture.quantiteNourriture;
    }
    
    
    public void soustraireNourriture(){
        this.nourriture.quantiteNourriture--;
    }
    
    public void setEnReproduction(){
        this.neuneu1.setEnReproduction(true);
    }

    
    
    
    
    
    
    
    
}
