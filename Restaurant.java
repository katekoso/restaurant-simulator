import java.util.TreeMap;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

/**
 * Class Restaurant used to model a real-life restaurant.
 * 
 * @author Ekaterina Kosourikhina
 * @version 05.05.25
 */
public class Restaurant {
    private TreeMap<Integer, Dish> dishes;

    /**
     * Create a new Restaurant with an empty list of dishes.
     */
    public Restaurant() {
        dishes = new TreeMap<>();
    }

    public static void main(String[] args) {
        Restaurant res = new Restaurant();
        res.populate();
        res.displayMap(res.getDishes());
    }

    /**
     * Get dishes.
     * 
     * @return dishes.
     */
    public TreeMap<Integer, Dish> getDishes() {
        return dishes;
    }

    /**
     * Create a new dish and add it to dishes.
     * 
     * @param name      The name of the dish.
     * @param price     The price of the dish.
     * @param isStopped The status of the dish on a stop list.
     * @return true if the dish was successfully added, false if it was already in
     *         dishes and
     *         wasn't added.
     */
    public boolean addDish(String name, double price, boolean isStopped) {
        Dish dish = new Dish(name, price, isStopped);
        Dish returnedObj = dishes.putIfAbsent(dish.getId(), dish);
        if (returnedObj == null) {
            return true;
        }
        return false;
    }

    /**
     * Clear all entries from dishes.
     */
    public void clear() {
        dishes.clear();
    }

    /**
     * Populate dishes with example entries.
     */
    public void populate() {
        clear();
        addDish("tomato soup", 2.50, false);
        addDish("pasta bolognese", 4.00, false);
        addDish("greek salad", 1.25, true);
    }

    /**
     * Remove a dish from dishes.
     * 
     * @param id An id of the dish to be removed.
     * @return true if the dish was found and deleted, false if it wasn't found.
     */
    public boolean deleteDish(int id) {
        Dish deletedDish = dishes.remove(id);
        if (deletedDish != null) {
            return true;
        }
        return false;
    }

    /**
     * Update a dish's status on the stop list.
     * 
     * @param id        An id of the dish which status is to be updated.
     * @param isStopped A new status on the stop list.
     * @return true if the status was different from the one already recorded and it
     *         was
     *         successfully updated. false if the status was the same and it wasn't
     *         updated.
     */
    public boolean updateStoplist(int id, boolean isStopped) {
        Dish updatedDish = dishes.get(id);
        boolean isStoppedNow = updatedDish.getIsStopped();
        if (isStoppedNow == isStopped) {
            return false;
        }
        updatedDish.setIsStopped(isStopped);
        return true;
    }

    /**
     * Return either dishes available to serve or the ones on the stop list.
     * 
     * @param isStopped The status based on which dishes are filtered.
     * @return dishes that have the status needed.
     */
    public TreeMap<Integer, Dish> getAvailableOrStoplist(boolean isStopped) {
        TreeMap<Integer, Dish> dishesToReturn = new TreeMap<>();
        for (int key : dishes.keySet()) {
            Dish dish = dishes.get(key);
            if (dish.getIsStopped() == isStopped) {
                dishesToReturn.put(dish.getId(), dish);
            }
        }
        return dishesToReturn;
    }

    /**
     * Return a total price of all the dishes in the restaurant.
     * 
     * @return a total price of all the dishes.
     */
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (int key : dishes.keySet()) {
            Dish dish = dishes.get(key);
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    /**
     * Display the contents of a map
     */
    public void displayMap(TreeMap<Integer, Dish> dishes) {
        for (int key : dishes.keySet()) {
            Dish dish = dishes.get(key);
            System.out.println("\tDish " + key + "\n" + dish);
        }
    }

    /**
     * Create a CSV file, write the contents of dishes in it.
     * 
     * @param filename The name of a file to be created.
     * @param dishes   A TreeMap of dishes to be written in the file.
     * @return true if the file was successfully written in, false if something went
     *         wrong.
     */
    public static boolean writeCSVFile(String filename, TreeMap<Integer, Dish> dishes) {
        boolean success = false;
        try (FileWriter writer = new FileWriter(filename)) {
            for (int key : dishes.keySet()) {
                Dish dish = dishes.get(key);
                writer.write(Integer.toString(key) + ",");
                writer.write(dish.getName() + ",");
                String price = String.format(Locale.US, "%.2f", dish.getPrice());
                writer.write(price + ",");
                writer.write(Boolean.toString(dish.getIsStopped()));
                writer.write('\n');
            }

            success = true;
        } catch (IOException e) {
            System.err.println("There was a problem writing to " + filename);
        }
        return success;
    }
}