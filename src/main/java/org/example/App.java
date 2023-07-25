package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class App {
    List<Location> locationList = new ArrayList<>();
    List<Dish> dishList = new ArrayList<>();
    List<Restro> restroList = new ArrayList<>();
    void parseRestroData() throws IOException{
        BufferedReader inDish = Files.newBufferedReader(Paths.get("C:\\Users\\rhythm.chouksey\\Documents\\GitHub\\DEC22-SA\\data\\restro.csv"));
        String line = null;
        Restro tempRestro = null;
        for(int cntr = 0; (line = inDish.readLine()) != null; ++cntr) {
            String[] restroData = line.split(",");
            tempRestro = new Restro();
            tempRestro.setId(restroData[0]);
            tempRestro.setName(restroData[1]);
            tempRestro.setMenu((List) this.dishList.stream().filter((dish) -> {
                return dish.getRestroId().equals(restroData[0]);
            }).collect(Collectors.toList()));
            Optional tempLocation;
            if ((tempLocation = this.locationList.stream().filter((location) -> {
                return location.getRestroId().equals(restroData[0]);
            }).findFirst()).isPresent()) {
                tempRestro.setLocation((Location) tempLocation.get());
            }
            this.restroList.add(cntr, tempRestro);
        }
    }
    void parseDishData() throws IOException{
        BufferedReader inDish = Files.newBufferedReader(Paths.get("C:\\Users\\rhythm.chouksey\\Documents\\GitHub\\DEC22-SA\\data\\dish.csv"));
        String line = null;
        for(int cntr = 0; (line = inDish.readLine()) != null; ++cntr) {
            String[] dishData = line.split(",");
            Dish tempDish = new Dish();
            tempDish.setId(dishData[0]);
            tempDish.setRestroId(dishData[1]);
            tempDish.setName(dishData[2]);
            tempDish.setPrice(Float.valueOf(dishData[3]));
            this.dishList.add(cntr, tempDish);
        }
    }
    void parseLocation() throws IOException {
        BufferedReader inLocation = Files.newBufferedReader(Paths.get("C:\\Users\\rhythm.chouksey\\Documents\\GitHub\\DEC22-SA\\data\\location.csv"));
        String line = null;

        for(int cntr = 0; (line = inLocation.readLine()) != null; ++cntr) {
            String[] locationData = line.split(",");
            Location tempLocation = new Location();
            tempLocation.setRestroId(locationData[0]);
            tempLocation.setLatx(Integer.valueOf(locationData[1]));
            tempLocation.setLony(Integer.valueOf(locationData[2]));
            this.locationList.add(cntr, tempLocation);
        }

    }

    public static void main(String[] args) throws IOException {//APP_CONTEXT
        App app = new App();
        app.parseLocation();
        app.parseDishData();
        app.parseRestroData();

        System.out.println("Welcome To Swiggy App");
        System.out.println("Would you like to Browse (1)");
        Scanner input1 = new Scanner(System.in);
        int sbchoice = input1.nextInt();
        if (sbchoice == 1) {
            app.browse();
        }
        else {
            System.out.println("Invalid Choice");
        }
    }
    void browse() {
        System.out.println("*********************************************");
        int restroCntr = 0;
        Iterator var2 = this.restroList.iterator();

        while(true) {
            Restro restro;
            do {
                if (!var2.hasNext()) {
                    this.createOrder((List)null);
                    return;
                }

                restro = (Restro)var2.next();
            } while(restro == null);

            System.out.println(restroCntr + 1 + ". " + restro.getName());
            int menuCntr = 0;
            Iterator var5 = restro.getMenu().iterator();

            while(var5.hasNext()) {
                Dish dish = (Dish)var5.next();
                if (dish != null) {
                    System.out.println("   " + (menuCntr + 1) + ". " + dish.getName() + " INR " + dish.getPrice());
                    ++menuCntr;
                }
            }

            ++restroCntr;
            System.out.println("*********************************************");
        }
    }
    void createOrder(List<Restro> orderRestroList) {
        if (orderRestroList == null) {
            orderRestroList = this.restroList;
        }

        System.out.print("Please enter the Restro and Dishes in the following format: Restro,Dish1,Qty1,Dish2,Qty2...");
        Scanner orderInput = new Scanner(System.in);
        String orderString = orderInput.next();
        int orderAmnt = 0;
        String[] orderData = orderString.split(",");
        int restroIndex = Integer.valueOf(orderData[0]);
        List<Dish> menu = ((Restro)orderRestroList.get(restroIndex - 1)).getMenu();
        Map<Dish, Integer> order = new HashMap();
        List<String> orderDataList = new ArrayList();

        int cntr;
        for(cntr = 1; cntr < orderData.length; ++cntr) {
            orderDataList.add(orderData[cntr]);
        }

        for(cntr = 0; cntr < orderDataList.size(); ++cntr) {
            Dish tenpDish = (Dish)((Restro)orderRestroList.get(restroIndex - 1)).getMenu().get(Integer.parseInt((String)orderDataList.get(cntr)) - 1);
            Integer tempQty = Integer.valueOf((String)orderDataList.get(cntr + 1));
            order.put(tenpDish, tempQty);
            ++cntr;
        }

        PrintStream var10000 = System.out;
        Object var10001 = orderRestroList.get(restroIndex - 1);
        var10000.println("You chose :" + ((Restro)var10001).getName());
        cntr = 1;

        for(Iterator var15 = order.entrySet().iterator(); var15.hasNext(); ++cntr) {
            Map.Entry entry = (Map.Entry)var15.next();
            Dish dish = (Dish)entry.getKey();
            Integer qty = (Integer)entry.getValue();
            System.out.println("    " + cntr + ". " + dish.getName() + " X " + (Integer)entry.getValue());
            orderAmnt = (int)((float)orderAmnt + dish.getPrice() * (float)qty);
        }

        System.out.println("Total Order Amount: " + orderAmnt);
    }
}