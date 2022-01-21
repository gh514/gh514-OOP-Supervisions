public class Q6 {

    public static class Singleton {
        private static Singleton MySingleton;
        private Singleton(){}

        private static Singleton Instantiate(){
            if (MySingleton == null) {
                MySingleton = new Singleton();
            }
            return MySingleton;
        }

        public void Message(){
            System.out.println("Hello World!");
        }
    }


    public static void main(String[] args) {

        Singleton SingleObject = Singleton.Instantiate();
        SingleObject.Message();
    }
}
