import java.awt.image.PixelGrabber;
import java.util.ArrayList;
import java.util.List;

public class Q7 {
    public static abstract class Animal {

    }

    public static class Fish extends Animal {
        public static void swim(){
            System.out.println("Swim");
        }
    }

    public static class Bird extends Animal {
        public static void flap(){
            System.out.println("Flap");
        }
    }

    public static abstract class Zoo {

        public Zoo() {
            Animal pufferfish = makeAnimal();
            Animal pigeon = makeAnimal();
        }

        abstract protected Animal makeAnimal();
    }

    public static class Aviary extends Zoo {
        @Override
        protected Animal makeAnimal() {
            return new Bird();
        }
    }

    public static class Aquarium extends Zoo {
        @Override
        protected Animal makeAnimal() {
            return new Fish();
        }
    }

    public static void main(String[] args) {
        Zoo CambridgeAquarium = new Aquarium();
        Zoo CambridgeBirdSanctuary = new Aviary();
    }

}
