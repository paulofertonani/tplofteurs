/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

/**
 *
 * @author paulo
 */
public class Cannibale extends Neuneu{
    
    public Cannibale(int energie, int typeNeuneu, Loft monLoft, int position[]){
        super(energie, typeNeuneu, monLoft, position);
        this.nomImage = "cannibale.png";
    }  

    @Override
    public void manger() {
        //vérifier si il y a une nourriture dans la case oú le neuneu se trouve
        if(this.monLoft.c[this.position[0]][this.position[1]].getNourriture() != null){
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            this.monLoft.c[this.position[0]][this.position[1]].soustraireNourriture();
            //vérifier si la lourriture est dejà fini, et la effacer dans ce cas
            if( this.monLoft.c[this.position[0]][this.position[1]].getQuantiteNourriture() <=0  ){
                this.effacerNourriture( this.monLoft.c[this.position[0]][this.position[1]].getNourriture() );
            }
            

        }
    }

    @Override
    public void reproduire() {
        //cette classe ne se reproduit pas
    }

    @Override
    public void deplacer() {
        this.deplacerParHazard();
        System.out.println("energie:");
        System.out.print(this.energie);
    }
    
    @Override
    public boolean cannibale(){
        
        if( (this.monLoft.caseCannibale(this.position[0] - 1, this.position[1] - 1) )){
            
            this.monLoft.c[this.position[0] - 1][this.position[1] - 1].neuneu1.tuerNeuneu();
            //this.monLoft.c[this.position[0] - 1][this.position[1] - 1].setNeuneu(this);
            //this.position[0]--;
            //this.position[1]--;
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;
        }
        else if( (this.monLoft.caseCannibale(this.position[0], this.position[1] - 1) )){
            this.monLoft.c[this.position[0]][this.position[1] - 1].neuneu1.tuerNeuneu();    
            //this.monLoft.c[this.position[0]][this.position[1] - 1].setNeuneu(this);
            //this.position[1]--;
            //this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;
                
        }
        else if((this.monLoft.caseCannibale(this.position[0] + 1, this.position[1] - 1) )){
            this.monLoft.c[this.position[0] + 1][this.position[1] - 1].neuneu1.tuerNeuneu();
            //this.monLoft.c[this.position[0] + 1][this.position[1] - 1].setNeuneu(this);
            //this.position[0]++;
            //this.position[1]--;
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;
        }
        else if((this.monLoft.caseCannibale(this.position[0] + 1, this.position[1]) )){
            this.monLoft.c[this.position[0] + 1][this.position[1]].neuneu1.tuerNeuneu(); 
            //this.monLoft.c[this.position[0] + 1][this.position[1]].setNeuneu(this);
            //this.position[0]++;
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;
        }
        else if((this.monLoft.caseCannibale(this.position[0] + 1, this.position[1] + 1) )){
            this.monLoft.c[this.position[0] + 1][this.position[1] + 1].neuneu1.tuerNeuneu();
            //this.monLoft.c[this.position[0] + 1][this.position[1] + 1].setNeuneu(this);
            //this.position[0]++;
            //this.position[1]++;
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;
        }
        else if((this.monLoft.caseCannibale(this.position[0], this.position[1] + 1) )){
            this.monLoft.c[this.position[0]][this.position[1] + 1].neuneu1.tuerNeuneu(); 
            //this.monLoft.c[this.position[0]][this.position[1] + 1].setNeuneu(this);
            //this.position[1]++;
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;

        }
        else if((this.monLoft.caseCannibale(this.position[0] - 1, this.position[1] + 1) )){
            this.monLoft.c[this.position[0] - 1][this.position[1] + 1].neuneu1.tuerNeuneu(); 
            //this.monLoft.c[this.position[0] - 1][this.position[1] + 1].setNeuneu(this);
            //this.position[0]--;
            //this.position[1]++;
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;
        }
        else if((this.monLoft.caseCannibale(this.position[0] - 1, this.position[1]) )){
            this.monLoft.c[this.position[0] - 1][this.position[1]].neuneu1.tuerNeuneu();
            //this.monLoft.c[this.position[0] - 1][this.position[1]].setNeuneu(this);
            //this.position[0]--;
            this.energie = this.energie + this.monLoft.getEnergieNourriture();
            System.out.println("cannibale!");
            return true;
        }
        return false;
        
        
    }
    
}
