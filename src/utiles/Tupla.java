/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utiles;

/**
 *
 * @author joan
 * @param <T>
 * @param <K>
 */
public class Tupla<T, K> {
    
    private T elemento1;
    private K elemento2;
    
    public Tupla(T elemento1, K elemento2){
        this.elemento1 = elemento1;
        this.elemento2 = elemento2;
    }

    public T getElemento1() {
        return elemento1;
    }

    public void setElemento1(T elemento1) {
        this.elemento1 = elemento1;
    }

    public K getElemento2() {
        return elemento2;
    }

    public void setElemento2(K elemento2) {
        this.elemento2 = elemento2;
    }
    
    
    
}
