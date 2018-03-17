import java.util.Scanner;

public class Tarkpea {

    public static void main(String[] args) throws Exception{
        Pakkuja.setArgs(args);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Sisesta küsimuse osa: ");
            if (Pakkuja.paku(scan, scan.nextLine())) {
                break;
            }
        }
    }
}