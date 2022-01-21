import com.sun.java.accessibility.util.AccessibilityListenerList;

import java.util.Comparator;
import java.util.Scanner;

public class Q10 {
    static Scanner scanner;
    static Dead dead = new Dead();

    static Stuffed stuffed = new Stuffed();
    static Satiated satiated = new Satiated();
    static Peckish peckish = new Peckish();
    static Famished famished = new Famished();
    static Starved starved = new Starved();

    static State[] hungerarray = {dead, starved, famished, peckish, satiated, stuffed};

    static Alert alert = new Alert();
    static Perky perky = new Perky();
    static Drowsy drowsy = new Drowsy();
    static Sleepy sleepy = new Sleepy();
    static Exhausted exhausted = new Exhausted();

    static State[] fatiguearray = {dead, exhausted, sleepy, drowsy, perky, alert};

    static Ecstatic ecstatic = new Ecstatic();
    static Content content = new Content();
    static Bored bored = new Bored();
    static Annoyed annoyed = new Annoyed();
    static Fuming fuming = new Fuming();

    static State[] happinessarray = {dead, fuming, annoyed, bored, content, ecstatic};

    public static class State{
        int rank;

        public State(int rank){
            this.rank = rank;
        }

        public State AddState(State state){
            State[] temparray = {};

            if (this instanceof Hunger) {
                temparray = hungerarray;
            } else if (this instanceof Fatigue) {
                temparray = fatiguearray;
            } else {
                temparray = happinessarray;
            }

            if ((this.rank+state.rank)>=5){
                return temparray[5];
            } else if (this.rank+state.rank <= 0){
                return temparray[0];
            } else {
                return temparray[this.rank+state.rank];
            }
        }

        public State SubState(State state){
            State[] temparray = {};

            if (this instanceof Hunger) {
                temparray = hungerarray;
            } else if (this instanceof Fatigue) {
                temparray = fatiguearray;
            } else {
                temparray = happinessarray;
            }

            if ((this.rank-state.rank)>=5){
                return temparray[5];
            } else if (this.rank-state.rank <= 0){
                return temparray[0];
            } else {
                return temparray[this.rank-state.rank];
            }
        }
    }

    public static class Dead extends State{
        public Dead(){super(0);}
    }


    public static abstract class Hunger extends State{ public Hunger(int rank) {super(rank);}}
    public static class Stuffed extends Hunger{public Stuffed() {super(5);}}
    public static class Satiated extends Hunger{ public Satiated() {super(4);}}
    public static class Peckish extends Hunger{ public Peckish() {super(3);}}
    public static class Famished extends Hunger{ public Famished() {super(2);}}
    public static class Starved extends Hunger{ public Starved() {super(1);}}

    public static abstract class Fatigue extends State{ public Fatigue(int rank) {super(rank);}}
    public static class Alert extends Fatigue{ public Alert() {super(5);}}
    public static class Perky extends Fatigue{ public Perky() {super(4);}}
    public static class Drowsy extends Fatigue{ public Drowsy() {super(3);}}
    public static class Sleepy extends Fatigue{ public Sleepy() {super(2);}}
    public static class Exhausted extends Fatigue{ public Exhausted() {super(1);}}

    public static abstract class Happiness extends State{ public Happiness(int rank) {super(rank);}}
    public static class Ecstatic extends Happiness{ public Ecstatic() {super(5);}}
    public static class Content extends Happiness{ public Content() {super(4);}}
    public static class Bored extends Happiness{ public Bored() {super(3);}}
    public static class Annoyed extends Happiness{ public Annoyed() {super(2);}}
    public static class Fuming extends Happiness{ public Fuming() {super(1);}}

    public static abstract class Diet{}
    public static class Carnivore extends Diet{}
    public static class Omnivore extends Diet{}
    public static class Herbivore extends Diet{}

    public static abstract class Food{
        Hunger nutrition;
        Diet diet;

        public Food(Hunger nutrition, Diet diet){
            this.nutrition = nutrition;
            this.diet = diet;
        }
    }
    public static class Beef extends Food{
        public Beef(){
            super(stuffed, new Carnivore());
        }
    }

    public static class Lettuce extends Food{
        public Lettuce(){
            super(peckish, new Herbivore());
        }
    }

    public static class Nuts extends Food{
        public Nuts(){
            super(satiated, new Omnivore());
        }
    }

    public static class Reject extends Exception{
        public Reject(){}
    }

    public static class Back extends Exception{
        public Back(){}
    }

    public static class Pet{
        String name;
        Hunger hunger = satiated;
        Fatigue fatigue = perky;
        Happiness happiness = content;
        Diet diet;
        String species;
        Boolean alive = true;
        public Throwable Death;
        public Throwable Reject;
        public Throwable Back;

        public Pet(String species, Diet diet){
            this.species = species;
            this.diet = diet;
        }

        public Pet() {}

        public void play() throws Throwable {

            if (fatigue.SubState(sleepy).equals(dead)){
                throw new Back();
            } else {
                fatigue = (Fatigue) fatigue.SubState(sleepy);
            }

            if (hunger.SubState(famished).equals(dead)){
                throw new Back();
            } else {
                hunger = (Hunger) hunger.SubState(famished);
            }

            happiness = (Happiness) happiness.AddState(content);
        }

        public void sleep() throws Throwable {
            if (hunger.SubState(starved).equals(dead)){
                throw new Back();
            } else {
                hunger = (Hunger) hunger.SubState(starved);
            }
            fatigue = new Alert();
        }

        public void eat(Food food) throws Throwable{
            if (diet instanceof Omnivore | food.diet instanceof Omnivore | diet.equals(food.diet)){
                if (hunger instanceof Stuffed){
                    throw new Back();
                }
                hunger = (Hunger) hunger.AddState(stuffed);
            } else throw new Back();
        }
    }

    public static class Dog extends Pet{
        public Dog(){
            super("Dog", new Carnivore());
        }
    }

    public static class Rabbit extends Pet{
        public Rabbit(){
            super("Rabbit", new Herbivore());
        }
    }

    public static class Parrot extends Pet{
        public Parrot(){
            super("Parrot", new Omnivore());
        }
    }


    public static void main(String[] args) throws Throwable {
        scanner = new Scanner(System.in);
        boolean done = false;
        String input;
        Pet pet = new Pet();
        while (!done){
            done = true;
            System.out.println("Would you like a:\n1) Dog\n2) Rabbit\n3) Parrot?");
            pet.species = scanner.nextLine();
            if (pet.species.equals("1")){
                pet = new Dog();
            } else if (pet.species.equals("2")){
                pet = new Rabbit();
            } else if (pet.species.equals("3")) {
                pet = new Parrot();
            } else {
                done = false;
            }
        }
        System.out.format("What would you like to name your %s\n", pet.species);
        pet.name = scanner.nextLine();

        while (pet.alive){
            System.out.format("Press:\n1) to play with %s\n2) to feed them\n3) to let them sleep\n", pet.name);
            input = scanner.nextLine();
            if (input.equals("1")){
                try {
                    pet.play();
                    System.out.format("You play with %s\n", pet.name);
                } catch (Back errorMessage){
                    System.out.format("Playing will kill %s, are you sure?\ny/n\n", pet.name);
                    if (scanner.nextLine().equals("y")){
                        System.out.format("You play with %s\n", pet.name);
                        pet.alive = false;
                    }
                }
            } else if (input.equals("2")){
                System.out.format("What would you like to feed %s?\n", pet.name);
                System.out.println("1) Beef\n2) Lettuce\n3) Nuts");
                input = scanner.nextLine();
                if (input.equals("1")){
                    try {
                        pet.eat(new Beef());
                        System.out.format("%s eats the beef.", pet.name);
                    } catch (Back errorMessage){
                        System.out.format("%s wont eat it.\n", pet.name);
                    }
                } else if (input.equals("2")){
                    try {
                        pet.eat(new Lettuce());
                        System.out.format("%s eats the lettuce.", pet.name);
                    } catch (Back errorMessage){
                        System.out.format("%s wont eat it.\n", pet.name);
                    }
                } else if (input.equals("3")){
                    try {
                        pet.eat(new Nuts());
                        System.out.format("%s eats the nuts.", pet.name);
                    } catch (Back errorMessage){
                        System.out.format("%s wont eat it.\n", pet.name);
                    }
                } else System.out.println("Invalid input");
            } else if (input.equals("3")){
                try {
                    pet.sleep();
                    System.out.format("%s sleeps soundly.\n", pet.name);
                } catch (Back errorMessage){
                    System.out.format("Sleeping will kill %s, are you sure?\ny/n\n", pet.name);
                    if (scanner.nextLine().equals("y")){
                        pet.alive = false;
                    }
                }
            } else {
                System.out.println("Invalid input\n");
            }
        }
        System.out.format("%s has died.", pet.name);;
    }
}
