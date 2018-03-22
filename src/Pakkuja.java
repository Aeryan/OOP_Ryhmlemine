import java.util.ArrayList;
import java.util.Scanner;

// Klass sobivate küsimuste otsimiseks ja kasutajaga suhtlemiseks
public class Pakkuja {
    // Muutujad võimalike saadavate argumentide, sobivate küsimuste ja terve küsimuste komplekti tarbeks
    private static String[] args;
    private static ArrayList<Küsimus> vasted;
    private static ArrayList<Küsimus> eksam = new ArrayList<>();

    // Meetod argumentide seadistamiseks
    public static void setArgs(String[] args) throws Exception{
        Pakkuja.args = args;

        // Kui argumente käivitamisel ei antud, loob Pakkuja objekti Lugeja, mis loeb näitefaili sisu
        if (Pakkuja.args.length == 0) {
            Lugeja logistik = new Lugeja();
            eksam = logistik.loe();
        }
        // Kui argumente anti, kasutab Lugeja esimest argumenti failinimena, mida küsimuste lugemisel kasutada
        else {
            Lugeja logistik = new Lugeja(Pakkuja.args[0]);
            eksam = logistik.loe();
        }
    }

    // Meetod küsimuste kuvamiseks, valimiseks, parameetrite esitamiseks ja vastuste saamiseks
    public static boolean paku(Scanner scan, String sisu) {

        Pakkuja.vasted = new ArrayList<>();
        // Kui kasutaja soovib rakenduse sulgeda, tagastab meetod väärtuse "true",...
        if (sisu.equals("q")) {
            System.out.println("Peatse jällenägemiseni!");
            return true;
        }
        // ... vastasel korral otsitakse küsimusi, milles sisalduks kasutaja poolt sisestatud sõne
        for (Küsimus i: eksam) {
            if (i.getKüsimus().toLowerCase().contains(sisu.toLowerCase())) {
                // Sobivad küsimused lisatakse nende jaoks loodud listi
                Pakkuja.vasted.add(i);
            }
        }
        // Kui küsimuste otsimine tulemusi ei andnud, tagastab meetod väärtuse "false"
        if (vasted.size() == 0) {
            System.out.println("Sellise sisuga küsimust andmestikus ei ole.\n");
            return false;
        }
        // Kui leidus sobivaid küsimusi, väljastatakse sobinute arv, üks sobiv küsimus ja edasised võimalused
        else {
            System.out.println("\nLeidus " + vasted.size() + " vastet.");
            int counter = (int) Math.round(Math.random() * (vasted.size()-1));
            while (true) {

                if (vasted.size() > 1) {
                    System.out.println((counter + 1) + ". vaste näeb välja selline:");
                }
                System.out.println(vasted.get(counter).getKüsimus().replace("&n&", "___"));
                // Kui küsimusel on mitu erinevat vastust, küsitakse kasutajalt soovitud parameetrite väärtusi
                if (vasted.get(counter).getVäärtused().get(0).size() > 1) {
                    System.out.println("Vastuse saamiseks sisesta semikoolonitega(;) eraldatult kõik muutujad\n" +
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
                    System.out.println();
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
                // Kui parameetrid annavad vastuse, siis see kuvatakse. Vastuse puudumisel küsitakse parameetreid uuesti
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
                // Kosmeetiline reavahe
                System.out.println();
            }
            return false;
        }

    }

}