/**
 * Class Dish used to model a dish in a restaurant.
 * 
 * @author Ekaterina Kosourikhina 
 * @version 30.04.25
 */
public class Dish implements Comparable<Dish>
{
    // A class variable to give each instance of the class a unique id
    private static int ID = 0;
    // A dish’s identifying number 
    private int id; 
    // A name of the dish 
    private String name;  
    // A price of a dish 
    private double price; 
    // Is the dish available to serve right now or is it on the stop list 
    private boolean isStopped; 

    /**
     * Create a new dish with a given name, price and a status on a stop
     * list. A unique id is generated automatically.
     */
    public Dish(String aName, double aPrice, boolean isStoppedNow)
    {
        id = ++ID;
        name = aName;
        price = aPrice;
        isStopped = isStoppedNow;
    }
    
    /**
     * Get the dish's id.
     * 
     * @return the dish's id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Get the dish's name.
     * 
     * @return the dish's name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get the dish's price.
     * 
     * @return the dish's price
     */
    public double getPrice()
    {
        return price;
    }
    
    /**
     * Set the dish's price.
     * 
     * @param aPrice the new price to be set.
     */
    public void setPrice(double aPrice)
    {
        price = aPrice;
    }
    
    /**
     * Get the dish's status on the stop list.
     * 
     * @return the dish's status on the stop list.
     */
    public boolean getIsStopped()
    {
        return isStopped;
    }
    
    /**
     * Set the dish's status on the stop list.
     * 
     * @param isStoppedNow the new status of the dish on the stop list.
     */
    public void setIsStopped(boolean isStoppedNow)
    {
        isStopped = isStoppedNow;
    }
    
    /**
     * Create a string representation of Dish.
     * 
     * @return A string in a particular format.
     */
    public String toString()
    {
        String str = "ID: %d%nName of the dish: %s%nPrice: %.2f%nIs it on the stop list: %s";
        String stopAnswer;
        if (isStopped == true) {
            stopAnswer = "yes";
        } else {
            stopAnswer = "no";
        }
        String result = String.format(str, id, name, price, stopAnswer);
        return result;
    }
    
    /**
     * Return true if two dishes are considered equal, because they 
     * have the same id, otherwise return false. 
     * @param o the Object to compare with
     * @return true if the receiver has the same id as the received Dish.
     */
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        
        if (o instanceof Dish) {
            Dish c = (Dish) o;
            return id == c.id;
        }
        
        return false;
    }
    
    /**
     * Provide a hash code for this dish, based on its id.
     * @return  a hashcode based on the id of the dish
     */
    public int hashCode()
    {
        int result = 17;
        result = 37 * result + id;
        return result;
    }
    
    /**
     * Return an integer as to whether this Dish should come before or
     * after the argument dish, or if it is equal to it in the ordering, based in id.
     * 
     * @param otherDish Another object that is compared to this one.
     * @return -1 if this Dish comes before the argument Dish, 0 if they
     * are equal, and +1 if this Dish comes after the argument Dish in order.
     */
    @Override
    public int compareTo(Dish otherDish)
    {
        if (id < otherDish.id) {
            return -1;
        }
        else if (id > otherDish.id) {
            return 1;
        }
        else {
            return 0;
        }
    }
}