public class ReturnValues {

    public static void main(String[] args) {
        double area1 = calculateArea (2.3, 3.6);
        printArea(2.3, 3.6, area1);
    
        String englishExplaination = explainArea("English");
        System.out.println(englishExplaination);
        
    }

    public static double calculateArea(double length, double width){

        if (length<0 || width<0) {
            System.out.println("INVALID DIMENTIIONS");
            System.exit(0);
            
        }

        double area = length * width;
        return area;
    }

    public static String explainArea(String language){

        switch (language) {
            case "English":
                return "Area equals length * width";
            case "French": 
                return "La surface est egale a la longueur * la largeur";
            case "Spanish":
                return "area es igual a largo * ancho";
            default:
                return "Language not available";
                
        }
        
    }

    public static void printArea(double length, double width, double area){
        System.out.println("A rectangle with a length of " + length + " and a width of " + width + " has an area " + area);
    }
    
}

// English "Area equals length * width"

// French "La surface est egale a la longueur * la largeur"

// Spanish "area es igual a largo * ancho"