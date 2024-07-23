package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static jdk.internal.org.jline.utils.Status.getStatus;
import static org.example.ListMenuButton.*;
import static java.lang.System.in;
import static java.lang.System.out;
import static org.example.DrinkType.CAPPUCCINO;
import static org.example.DrinkType.ESPRESSO;

public class CoffeeMachine {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(in));

    private int waterLevel;
    private int milkLevel;
    private int coffeBeans;
    private boolean isOn;

    public boolean getIsOn() {
        return isOn;
    }

    public void setIsOn(boolean isOn) {
        this.isOn = isOn;
    }

    private final int maxWaterLevel = 100;
    private final int maxMilkLevel = 50;
    private final int maxCoffeBeans = 20;

    private int drinksPrepared;
    private static final int CLEANING_THRESHOLD = 10;

    public CoffeeMachine(int waterLevel, int milkLevel, int coffeBeans) {
        this.waterLevel = waterLevel;
        this.milkLevel = milkLevel;
        this.coffeBeans = coffeBeans;
        this.drinksPrepared = 0;
    }

    public int getCoffeBeans() {
        return coffeBeans;
    }

    public void setCoffe(int coffee) {
        this.coffeBeans =+ coffee;
    }

    public boolean prepareEspresso() {
        checkIfNeedsCleaning();
        if (waterLevel < ESPRESSO.getWater()) {
            out.println("Недостаточно воды для эсперессо!");
            return false;
        }
        setWaterLevel(getWaterLevel() - ESPRESSO.getWater());
        setMilkLevel(getMilkLevel() - ESPRESSO.getWater());
        drinksPrepared++;
        return true;
    }

    public boolean prepareCappuccino() {
        checkIfNeedsCleaning();
        if (waterLevel < CAPPUCCINO.getWater()) {
            out.println("Не достаточно воды для каппучино!");
            return false;
        }
        if (milkLevel < CAPPUCCINO.getWater()) {
            out.println("Не достаточно молока для каппучино!");
            return false;
        }
        setWaterLevel(getWaterLevel() - CAPPUCCINO.getWater());
        setMilkLevel(getMilkLevel() - CAPPUCCINO.getWater());
        drinksPrepared++;
        return true;
    }

    public void refillWater() {
        waterLevel = maxWaterLevel;
        out.println("Долив воды.");
    }

    public void refillMilk() {
        milkLevel = maxMilkLevel;
        out.println("Долив молока.");
    }

    private boolean checkIfNeedsCleaning() {
        if (drinksPrepared >= CLEANING_THRESHOLD) {
            out.println("Нужно очистить кофемашину...");
            return false;
        }
        if (drinksPrepared == 0) {
            out.println("Машина чистая!");
            return true;
        }
        return false;
    }

    void cleanMachine() {
        if (!checkIfNeedsCleaning()) {
            drinksPrepared = 0;
            out.println("Очистка кофемашины...");
        }
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public int getMilkLevel() {
        return milkLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public void setMilkLevel(int milkLevel) {
        this.milkLevel = milkLevel;
    }


    public void makeDrink(DrinkType type) {
    }

    private void checkIngredients(int requiredWater, int requiredMilk, String errorMessage) {
        if (waterLevel < requiredWater || milkLevel < requiredMilk) {
        }
    }

    public ListMenuButton inputItem() {
        ListMenuButton[] menu = values();
        int selectItem;
        int menuLength = 20;
        do {
            selectItem = insertNumber("\nВведите номер 0-" + menuLength + ": ");
        } while (selectItem < 0 || selectItem > menuLength);
        return menu[selectItem];
    }

    public int insertNumber(String s) {
        int count;
        String input = "";
        do {
            out.print(s);
            try {
                input = reader.readLine();
                count = Integer.parseInt(input);
                if (count >= 0) {
                    break;
                }
            } catch (NumberFormatException | IOException e) {
                switch (input) {
                    case "Каппучино" -> printDrinkType(CAPPUCCINO);
                    case "Эспрессо" -> printDrinkType(ESPRESSO);
                    default -> out.print("Неверный ввод\n");
                }
            }
        } while (true);
        return count;
    }

    public void printDrinkType(DrinkType drinkType) {
        if (getIsOn()) {
            out.printf("Рецепт" + drinkType + ": " + "вода- " + drinkType.getWater() + drinkType.getCoffee() + "\n");

        } else {
            out.print("Ошибка. Включите кофемашину\n");
        }
    }

    public void tornOnOff() {
        out.println("Машина выключена");
        boolean getStatus = false;
        if (getStatus) {
            System.out.println("Машина выключается...");
        } else {
            out.println("Машина включается...");

        }
        setIsOn(!getIsOn());

    }

    public void printMenu() {
        System.out.println("0. " + EXIT +text);
        System.out.println("1. " + POWER_ON +text);
        System.out.println("2. " + PRINT_CAPPUCCINO +text);
        System.out.println("3. " + CLEAR_MACHINE +text);
        System.out.println("4. " + ADD_WATER +text);
        System.out.println("5. " + ADD_COFFEE +text);
        System.out.println("6. " + ADD_MILK + text);
        System.out.println("7. " + PREPARE_CAPPUCCINO +text);
        System.out.println("8. " + PREPARE_ESPRESSO +text);
    }

    public int inputNumberMenu(String s) {
        int count;
        String input = "";
        do {
            out.print(s);
            try {
                input = reader.readLine();
                count = Integer.parseInt(input);
                if (count >= 0) {
                    break;
                }
            } catch (NumberFormatException | IOException e) {
                switch (input) {
                    case "Cappuccino" -> printDrinkType(CAPPUCCINO);
                    case "Espresso" -> printDrinkType(ESPRESSO);
                    default -> out.print("Invalid input\n");
                }
            }
        } while (true);
        return count;
    }

    public void refillCoffee(String ingredient) {
        int countAdded = inputNumberMenu("\nСколько добавить " + ingredient + "\n");
        setCoffe(getCoffeBeans() + countAdded);

    }

    public void printRecipe( DrinkType drinkType) {
        System.out.println("Рецепт " + drinkType + " : " + "Вода - " + drinkType. getWater() + drinkType + "Молоко - ");
    }

    public class Main {
        public static void main(String[] args) {
            DrinkType cappuccino = CAPPUCCINO;

            cappuccino.printRecipe();
        }
    }
}