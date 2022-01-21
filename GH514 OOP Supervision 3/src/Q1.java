
class Q1 {

    public static void main(String[] args) {

        System.out.println(StaticCircle.area(2));

        InstanceCube MyCube = new InstanceCube();

        System.out.println(MyCube.volume(6));

    }

}





class InstanceCube {



    public int length;



    public int volume (int input){

        length = input;

        return length * length * length;

    }

}





class StaticCircle {

    public static float pi = (float) 3.14;



    public static float area (int radius){

        return radius * radius * pi;

    }

}



