import java.util.Scanner;

// Peaklass
/* Uuritavat küsimuste komplekti saab muuta, andes selle klassi peameetodile argumendiks sõne sobivat komplekti
sisaldava faili asukohaga */
public class Tarkpea {

    public static void main(String[] args) throws Exception{
        // Loon staatilise klassi Pakkuja, millele annan edasi käivitamisel sisestatud argumendid.
        Pakkuja.setArgs(args);
        // Loon objekti scan, mis hakkab lugema kasutaja poolt sisestatud teksti.
        Scanner scan = new Scanner(System.in);
        // Küsin kasutajalt küsimusi osi seni, kuni ta soovib rakendusest väljuda.
        while (true) {
            System.out.print("Sisesta küsimuse osa (väljumiseks \"q\"): ");
            /* Pakkuja kontrollib saadud sisendi väärtust ning kui selleks on väljumiskäsk,
            tagastab Pakkuja väärtuse "true" ning lõpmatu kordus katkeb */
            if (Pakkuja.paku(scan, scan.nextLine())) {
                break;
            }
        }
    }
}