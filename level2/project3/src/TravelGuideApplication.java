// --== CS400 File Header Information ==--
// Name: Linxiu Zeng
// Email: lzeng37@wisc.edu
// Team: MF
// TA: Harit
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
import java.util.Scanner;

public class TravelGuideApplication {
  private static String enterLocation(Scanner scan) {
    String location = scan.nextLine();

    while (location == null || location.equals("")) {
      System.out.println("The location cannot be null, enter again.");
      location = scan.nextLine();

    }

    return location;
  }


  public static void main(String[] args) {
    TravelGuide travel = new TravelGuide();

    // load data into the backend object by using the data wrangler's code

    System.out.println("Welcome to our Travel Guide, enter 'q' to exit.");
    Scanner scan = new Scanner(System.in);
    String input = null;
    try {
      do {
        System.out.println("\nValid commands: (p) Print all available travelling locations; "
            + "(s) Find the shortest path from A to B; (d) Find distance between A and B");

        System.out.println("What action do you want to perform? Enter commands: ");

        input = scan.nextLine().strip();

        switch (input.toLowerCase()) {
          // print all available travelling location
          case ("p"): {
            System.out.println("enter the p case");

            System.out.println(travel.toString());
          }
          // find the shortest path from A to B
          case ("s"): {
            System.out.println("Enter location A: ");
            TravelGuide.cityNode locationA = travel.new cityNode(enterLocation(scan));

            System.out.println("Enter location B: ");
            TravelGuide.cityNode locationB = travel.new cityNode(enterLocation(scan));

            System.out.println("The shortest path from " + locationA.cityName + " to "
                + locationB.cityName + " is: ");

            try {
              // call backend person's code to find shortest path between location A and B, and also
              // handle possible exceptions if any occurs
              System.out.println(travel.getShortestTrainRoute(locationA, locationB));

            } catch (NoSuchElementException e) {
              System.out.println(e.getMessage());
              break;
            } catch (Exception e) {
              System.out.println(
                  "OPPPS! An Unexpected Exception occurs while looking for the shortest path.");
            }
          }

          // Find distance between A and B
          case ("d"): {
            System.out.println("Enter location A: ");
            TravelGuide.cityNode locationA = travel.new cityNode(enterLocation(scan));

            System.out.println("Enter location B: ");
            TravelGuide.cityNode locationB = travel.new cityNode(enterLocation(scan));

            System.out.println("The shortest distance from " + locationA.cityName + " to "
                + locationB.cityName + " is: ");
            
            // call backend person's code to find the distance between location A and B, and also
            // handle possible exceptions if any occurs
            try {
              travel.getShortestPathDistance(locationA, locationB);
            } catch (NoSuchElementException e) {
              System.out.println(e.getMessage());
              break;
            } catch (Exception e) {
              System.out.println(
                  "OPPPS! An Unexpected Exception occurs while looking for the shortest distance.");
            }
          }

          // quit the application
          case ("q"): {
            break;
          }

          default: {
            System.out.println("Your command is not valid, please re-enter the command.");
          }
        }

      } while (!input.equalsIgnoreCase("q"));

      System.out.println("Thanks for using our Travel Guide! See you next time!");
      scan.close();
    } catch (Exception e) {
      System.out.println("OPPPPS! Unexcepted Exception occurs.");

      System.out.println(
          "Thanks for choosing our Travel Guide and sorry for the inconvenience in the app."
              + "We will fix it as soon as possible.See you next time!");
      scan.close();
    }
  }
}
