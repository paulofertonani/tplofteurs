/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.objet.lofteurs;

/**
 * Dans ce classe, vous pouvez créer une nourriture ou une boisson en donnent 
 * ce paramètre, et aussi définir ce type (du pain, d'alcool, etc)
 * 
 * 
 * @author paulo
 */
public class Nourriture {
    private boolean isNourriture;
    private int typeNourriture;
    protected int quantiteNourriture;
    private int position[];
    private String nomImage;
    private Loft monLoft;
    
    public Nourriture(boolean isNourriture, int typeNourriture, int quantiteNourriture, Loft monLoft, int position[]){
        this.isNourriture = isNourriture;
        this.typeNourriture = typeNourriture;
        this.quantiteNourriture = quantiteNourriture;
        this.monLoft = monLoft;
        this.position = position;
        
        if(isNourriture){
            switch(typeNourriture){
                case 0:
                    this.nomImage = "pain.gif";
                    break;
                case 1:
                    this.nomImage = "gateau.png";
                    break;
                default:
                    this.nomImage = "nomEtrange!!!";
                    break;
                            
            }
        }
        else{
            switch(typeNourriture){
                case 0:
                    this.nomImage = "biere.gif";
                    break;
                case 1:
                    this.nomImage = "soda.png";
                    break;
                default:
                    this.nomImage = "nomEtrange!!!";
                    break;
                            
            }
        }
        
    }

    public int[] getPosition() {
        return position;
    }

    public String getNomImage() {
        return nomImage;
    }

    
    
    
    
}


