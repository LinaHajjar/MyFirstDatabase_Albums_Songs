import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UI {

    //hovedMenu method with switches
    public static void hovedMenu(Scanner input) throws SQLException {
        System.out.println("================================================================================");
        System.out.println("                     Main  menu                                                 ");
        System.out.println("================================================================================");
        System.out.println("                 Enter the number of the option you want                        ");
        System.out.println("================================================================================");
        System.out.println("  Press 1 for : See the list of all tracks.                                     ");//sorted by name, with specific duration, for a specific artist
        System.out.println("  Press 2 for : See the list of artists.                                        ");//sorted by name, according to the name of an album, according to the name of a song
        System.out.println("  Press 3 for : add an artist.                                                  ");
        System.out.println("  Press 4 for : add an album.                                                   ");
        System.out.println("  Press 5 for : add a track.                                                    ");
        System.out.println("  Press 6 for : edit a track.                                                   ");
        System.out.println("  Press 7 for : End the program                                                 ");

        int choice = UserInput.getIntInput("choose the option: ","wrong input, choose only an integer between 1 and 7",1,7);

        switch(choice){
            case 1: // DONE
                System.out.println("you chose option 1: See the list of all tracks sorted by name.");
                System.out.println();
                DataBaseHandler.seeListTracks(input);

                System.out.println();
                System.out.println("Returning to the main menu:");
                System.out.println();
                hovedMenu(input);
                break;

            case 2: // DONE
                System.out.println("you chose option 2: See the list of artists.");
                DataBaseHandler.seeListArtists(input);

                System.out.println();
                System.out.println("Returning to the main menu:");
                System.out.println();
                hovedMenu(input);
                break;

            case 3:
                System.out.println("you chose option 3: add an artist.");
                DataBaseHandler.add_artist(input);

                System.out.println();
                System.out.println("Returning to the main menu:");
                System.out.println();
                hovedMenu(input);
                break;

            case 4:
                System.out.println("you chose option 4: add an album.");


                System.out.println();
                System.out.println("Returning to the main menu:");
                System.out.println();
                hovedMenu(input);
                break;

            case 5:
                System.out.println("you chose option 5: add a track.");


                System.out.println();
                System.out.println("Returning to the main menu:");
                System.out.println();
                hovedMenu(input);
                break;

            case 6:
                System.out.println("you chose option 6: edit a track.");


                System.out.println();
                System.out.println("Returning to the main menu:");
                System.out.println();
                hovedMenu(input);
                break;

            case 7:
                System.out.println("Ending the program...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option.");
                System.out.println();
                System.out.println("Returning to the main menu:");
                System.out.println();
                hovedMenu(input);
                break;


        }
    }
}
