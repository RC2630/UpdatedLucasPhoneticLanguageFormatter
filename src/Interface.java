import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Scanner;

public class Interface {

    public static void main(String[] args) {

        try {

            System.out.print(

                    "\n(1) format raw text into LPL text" +     // effort       // implemented      // usable
                    "\n(2) format raw text into FLPL text" +    // effort       // implemented      // usable
                    "\n(3) format FLPL text into raw text" +    // effort

                    "\n(4) format FLPL text into LPL text" +
                    "\n(5) format raw text into IPA text" +     // effort       // implemented      // usable
                    "\n(6) format FLPL text into IPA text" +

                    "\n(7) format IPA text into raw text" +     // effort
                    "\n(8) format IPA text into LPL text" +
                    "\n(9) format IPA text into FLPL text" +

                    "\n(10) format LPL text into raw text" +    // effort
                    "\n(11) format LPL text into FLPL text" +
                    "\n(12) format LPL text into IPA text" +

                    "\n\nEnter your option here: "

            );

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            new Formatter().performFormattingOperation(option);

            System.out.print("\nYour formatting has been completed. Thank you for using this program!\n");

        } catch (CustomExceptions.FileIsEmptyException e) {

            System.out.print("\nSince the input file is empty, absolutely nothing has been done!\n");

        } catch (NotImplementedException e) {

            System.out.print("\nSorry, but this option has not been implemented yet!\n");

        } catch (CustomExceptions.NotAnOptionException e) {

            System.out.print("\nSorry, but the option you entered is not valid!\n");

        } catch (Throwable t) {

            System.out.print("\nOops! An error has occurred!\n");

        }

    }

}