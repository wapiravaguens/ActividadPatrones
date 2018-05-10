/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

/**
 *
 * @author Estudiante
 */
public class Builder {
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        PizzaBuilder hawaiianPizzabuilder = new HawaiianPizzaBuilder();
        PizzaBuilder spicyPizzaBuilder = new SpicyPizzaBuilder();
        Pizza pizza;
        
        waiter.setPizzaBuilder( hawaiianPizzabuilder );
        waiter.constructPizza();

        pizza = waiter.getPizza();
        
        System.out.println("Masa: " + pizza.getDough());
        System.out.println("Salsa: " + pizza.getSauce());
        System.out.println("Cubierta: " + pizza.getTopping());
        
        waiter.setPizzaBuilder( spicyPizzaBuilder );
        waiter.constructPizza();

        pizza = waiter.getPizza();
        
        System.out.println("Masa: " + pizza.getDough());
        System.out.println("Salsa: " + pizza.getSauce());
        System.out.println("Cubierta: " + pizza.getTopping());
    }
    
}
