import java.util.ArrayList;
import java.util.Scanner;

public class Pakkuja {
    private static ArrayList<Küsimus> vasted;
    private static String[] args;
    private static ArrayList<Küsimus> eksam = new ArrayList<>();

    public static void setArgs(String[] args) throws Exception{
        Pakkuja.args = args;

        if (Pakkuja.args.length == 0) {
            Lugeja logistik = new Lugeja();
            eksam = logistik.loe();
        }

        else {
            Lugeja logistik = new Lugeja(Pakkuja.args[0]);
            eksam = logistik.loe();
        }
    }

    public static boolean paku(Scanner scan, String sisu) {

        Pakkuja.vasted = new ArrayList<>();

        if (sisu.equals("q")) {
            System.out.println("Peatse jällenägemiseni!");
            return true;
        }

        for (Küsimus i: eksam) {
            if (i.getKüsimus().toLowerCase().contains(sisu.toLowerCase())) {
                Pakkuja.vasted.add(i);
            }
        }

        if (vasted.size() == 0) {
            System.out.println("Sellise sisuga küsimust andmestikus ei ole.");
            return false;
        }
        else {
            System.out.println("Leidus " + vasted.size() + " vastet.");
            int counter = 0;
            while (true) {

                if (vasted.size() > 1) {
                    System.out.println((counter + 1) + ". vaste näeb välja selline:");
                }
                System.out.println(vasted.get(counter).getKüsimus().replace("&n&", "___"));

                if (vasted.get(counter).getVäärtused().get(0).size() > 1) {
                    System.out.println("Vastuse saamiseks sisesta semikoolonitega(;) eraldatult kõik muutujad " +
                            "(näiteks kolme muutuja 5, 12 ja 4 korral oleks sisend 5;12;4).");

                }
                else {
                    System.out.println(vasted.get(counter).getVäärtused().get(0).get(0));
                }
                if (vasted.size() > 1) {
                    System.out.print("Järgmise vaste kuvamiseks \"j\", " +
                            "eelmise vaste kuvamiseks \"e\", " +
                            "uue küsimuse otsimiseks \"u\": ");
                }
                else {
                    System.out.print("Väljumiseks \"q\": ");
                }
                String tagasiside = scan.nextLine().toLowerCase();

                if (tagasiside.equals("u")) {
                    break;
                }
                else if (tagasiside.equals("q")) {
                    return true;
                }
                else if (tagasiside.equals("j")) {
                    counter += 1;
                    if (counter == vasted.size()) {
                        counter = 0;
                    }
                }
                else if (tagasiside.equals("e")) {
                    counter -= 1;
                    if (counter < 0) {
                        counter = vasted.size() - 1;
                    }
                }
                else if (vasted.get(counter).getVäärtused().get(0).size() > 1) {
                    String lahend = vasted.get(counter).Kontrolli(tagasiside.split(";"));
                    if (lahend.equals("")) {
                        System.out.println("Sellist parameetrite komplekti andmebaasis ei ole.");
                    }
                    else {
                        System.out.println(lahend + "\n");
                        return false;
                    }
                }
                else {
                    System.out.println("Mittesobiv käsk");
                }

                System.out.println();
            }
            return false;
        }

    }

}