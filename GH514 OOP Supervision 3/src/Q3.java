public class Q3 {



    public class Car implements Comparable<Car>{

        private String manufacturer;

        private int age;





        @Override

        public int compareTo(Car o) {

            int v = manufacturer.compareTo(o.manufacturer);

            if (v != 0) {

                return v;

            }

            return Integer.compare(age, o.age);

        }

    }

}

