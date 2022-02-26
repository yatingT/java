// --== CS400 File Header Information ==--
// Name: Ryan Brumm
// Email: rbrumm@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


import java.util.Scanner;

/**
 * @author ryanbrumm & Bill Yan & Linxiu Zeng
 *
 */
public class chemStockRoom {
  public static void main(String[] args) {
    ChemicalInterface chemTree = new ChemicalInterface();
    chemTree = chemTree.loadChemicalTree("C:\\Users\\Yatin\\Documents\\Fall2020\\cs400\\project2\\src\\ChemStockRoom.csv");

    System.out.println("Welcome to the chemistry stock room, to exit enter 'quit' ");
    Scanner scan = new Scanner(System.in);
    String input = null;
    do {
      System.out.println("valid commands include: (I) to insert new chemical"
          + " (C) to check if we have a certian chemical "
          + "(P) to print off amount of all chemicals "
          + " (R) to remove a chemical + (Quit) to quit the program");

      input = scan.nextLine().strip();

      switch (input.toLowerCase()) {
        case ("i"): {
          int quantity;

          System.out.println("What chemical would you like to add?");
          String name = scan.nextLine().strip();

          System.out.println("How much do you want to add?");
          quantity = Integer.parseInt(scan.nextLine());

          // Add base on whether the chemical is present already or not
          Chemical newChem = new Chemical(name, quantity);

          if (!chemTree.containsChemical(newChem)) {
            // The chemical is not present
            chemTree.addChemical((new Chemical(name, quantity)));
          } else {
            // The chemical is already existed in the tree, need to increase quantity
            Chemical existedChem = chemTree.getChemical(newChem);
            chemTree.addQuant(existedChem, newChem);
          }

          System.out.println(quantity + " grams of " + name + " have been added");
          break;
        }

        case ("c"): {
          System.out.println("What chemical would you like to check for?");
          String name = scan.nextLine().strip();

          // Split case base on whether the chemical is present or not
          Chemical check = new Chemical(name, 130);

          check = chemTree.getChemical(check);

          System.out.println(
              "There are " + chemTree.getChemicalQuant(check) + " grams of " + name + " left");

          break;
        }

        case ("p"): {
          System.out.println("Printing off all chemicals......");
          System.out.println(chemTree.printTree());
          break;
        }

        case ("r"): {
          System.out.println("Which chemical would you like to remove?");

          String name = scan.nextLine().strip();

          // Search to see if the chemical is present
          Chemical check = new Chemical(name, 0);

          if (!chemTree.containsChemical(check)) {
            System.out.println("Sorry, we are out of stock for " + name);
            break;
          }

          // The chemical is present
          int quantity = chemTree.getChemicalQuant(check);
          // the existed chemical in the tree
          Chemical remove = chemTree.getChemical(check);
          System.out.println("We have " + quantity + " grams of " + name + " in stock");
          System.out.println("How much would you like to check out?");

          int quan;
          while (true) {
            quan = Integer.parseInt(scan.nextLine());
            check.setChemicalQuant(quan);

            // Check if the amount is valid
            if (quan > quantity)
              System.out.println("Sorry, the amount you entered in is invalid");
            else
              break;
          }

          // Check how much is being removed
          chemTree.reduceQuant(remove, check);

          System.out
              .println("You have checked out " + quan + " grams of " + name + " successfully");

          break;
        }

        case ("quit"):{
          break;
        }

        default:
            System.out.println("Command not recognized.");
                }
    } while (!input.equalsIgnoreCase("quit"));

    scan.close();
    System.out.println("Thank you for using our chemistry stockroom application");
  }
}