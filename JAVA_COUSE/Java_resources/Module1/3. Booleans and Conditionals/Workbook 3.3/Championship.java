public class Championship {
    public static void main(String[] args) {

        //See Learn the Part for the complete instructions (link in resources folder of Udemy video).  

        int gryffindor = 1000;    //gryffindor points
        int ravenclaw = 1050;    //ravenclaw points

        // int margin = amount of points by which gryffindor scored over ravenclaw;
        int margin  = gryffindor - ravenclaw;

        if (margin >= 300) {
            System.out.println("Gryffindor takes the cup");
            
        }else if (margin >= 0) {
            System.out.println("In second palce, Gryffindor");
            
        }else if (margin >= -100) {
            System.out.println("In thrid place, Gryffindor");
        }else {
            System.out.println("In fouth place, Gryffindor");
        }
    }
}
