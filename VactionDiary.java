import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class VactionDiary {
    private static String validateDate(Scanner input, String prompt){
        boolean validInput = false;
        String dateString = " ";
        String end = "end";

        while (!validInput){
            validInput = true;
            try{
                System.out.print(prompt);
                dateString = input.nextLine();
                if (!dateString.equals(end)){
                    SimpleDateFormat sdfmt = new SimpleDateFormat("MMddyyyy");
                    sdfmt.setLenient(false);
                    Date javeDate = sdfmt.parse(dateString);
                    if (dateString.length() !=8){
                        throw new Exception();
                    }
                }
                else {
                    validInput = false;
                    return dateString;
                }
            }
            catch (Exception e){
                System.out.println("Invalid date format Please re-enter");
                validInput = false;
            }
        }
        return dateString;

    }
    private static String validateCityCountry(Scanner input, String prompt){
        System.out.print(prompt);
        String location = input.nextLine();
        if (location.length() <15){
            for (int i = location.length(); i < 15; i++){
                location += " ";
            }
        } else if (location.length() > 15) {
            String substring = location.substring(0, 15);
        }
        return location;
    }
    private static int validateDays (Scanner input, String prompt){
        boolean vaildInput = false;
        int numDays = 0;

        while (!vaildInput){
            vaildInput = true;
            try{
                System.out.print(prompt);
                numDays = input.nextInt();
                if (numDays <1 || numDays >30){
                    throw new Exception();
                }
            }
            catch (Exception e){
                System.out.println("Days must be between 1 and 30");
                vaildInput = false;
            }
        }
        return numDays;
    }
    private static String validateTravel(String prompt, Scanner input){
        boolean validInput = false;
        String travel = "";

        while (!validInput){
            validInput = true;
            try {
                System.out.println(prompt);
                travel = input.nextLine();
                if (!travel.equals("car") &&
                        !travel.equals("airplane") &&
                        !travel.equals("train") &&
                        !travel.equals("ship") &&
                        !travel.equals("bus")) {
                    throw new Exception();
                }
            }
            catch (Exception e) {
                    System.out.println("Please enter a valid travel mode");
                    validInput = false; //keep in error loop
                }
        }
        return travel;

    }
    public static void main(String[] args) {
        //Welecome message
        System.out.println("Welcome to your Vacation Diary");
        //prompt for the user to enter
        System.out.println("Please enter information Below:");
        Scanner input = new Scanner(System.in);

        //user wants to keep going
        String addMore = " ";

        //num of places
        int numStops = 0;
        final int MAX_STOPS = 10;
        String dateStarted[] = new String[MAX_STOPS];
        String cityVisited[] = new String[MAX_STOPS];
        String countryVisited[] = new String[MAX_STOPS];
        int numDays[] = new int[MAX_STOPS];
        String travelMode[] = new String[MAX_STOPS];

        while (addMore != "end") {
            String nextQuestion = "Enter the date the Vacation start (mmddyyyy) or end to quit: ";
            dateStarted[numStops] = validateDate(input, nextQuestion);
            if (dateStarted[numStops].equals("end")) {
                addMore = "end";
                continue;
            }
            nextQuestion = "Enter City Visited:";
            cityVisited[numStops] = validateCityCountry(input, nextQuestion);
            nextQuestion = "Enter the country Visited: ";
            countryVisited[numStops] = validateCityCountry(input, nextQuestion);
            nextQuestion = "Enter the number of days: ";
            numDays[numStops] = validateDays(input, nextQuestion);
            String ignore = input.nextLine();
            nextQuestion = "Which travel method used(car, airplane, bus, ship, train): ";
            travelMode[numStops] = validateTravel(nextQuestion, input);

            numStops++;
        }
        //close Scanner
        input.close();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~~~~~~~~~~~~~Vacation Diary~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Date\t\tCity\t\tCountry\t\tDays\t\tMode");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        for (int i = 0; i < numStops; i++){
            System.out.println(dateStarted[i] + "\t" +
                                cityVisited[i] + "\t" +
                                countryVisited[i] + "\t" +
                                numDays[i] + "\t\t" +
                                travelMode[i]);
        }
        //end
        System.out.println("Bye!!");

    }
}
