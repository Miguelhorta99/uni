public class Doubles {
    public static void main(String[] args) {

        int dice1 = rollDice();
        int dice2 = rollDice();

        System.out.println("Dice 1: " + dice1);
        System.out.println("Dice 2: " + dice2 + "\n");

        while (dice1 != dice2) {
            dice1 = rollDice();
            dice2 = rollDice();

            System.out.println("Dice 1: " + dice1);
            System.out.println("Dice 2: " + dice2 + "\n");
        }

        System.out.println("You rolled doubles!");

    }

    public static int rollDice() {
        int dice1 = 0;
        double dice = Math.random() * 7;
        dice1 = (int) dice;
        return dice1;
    }

}
