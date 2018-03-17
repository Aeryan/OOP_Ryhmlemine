import java.util.ArrayList;

public class Lugeja {
    public String failinimi;

    public Lugeja() {
        this.failinimi = "Küsimused.txt";
    }

    public Lugeja(String failinimi) {
        this.failinimi = failinimi;
    }

    public ArrayList<Küsimus> loe() throws Exception{
        java.io.File fail = new java.io.File(this.failinimi);
        java.util.Scanner sc = new java.util.Scanner(fail, "UTF-8");

        ArrayList<Küsimus> küsimustik = new ArrayList<>();

        while (sc.hasNextLine()) {

            ArrayList<ArrayList<String>> sõnestik = new ArrayList<>();
            ArrayList<String> variant = new ArrayList<>();
            StringBuilder küss = new StringBuilder();
            String seisund = "küsimus";

            while (true) {
                String rida = sc.nextLine();

                if (rida.equals("@")) {
                    seisund = "parameetrid";
                }
                else if (rida.equals("@@")){
                    sõnestik.add(variant);
                    variant = new ArrayList<>();
                    seisund = "parameetrid";
                }
                else if (rida.equals("@@@")){
                    sõnestik.add(variant);
                    Küsimus a = new Küsimus(küss.toString(), sõnestik);
                    küsimustik.add(a);
                    break;
                }
                else {
                    if (seisund.equals("küsimus")) {
                        küss.append(rida);
                        küss.append("\n");
                    }
                    else if (seisund.equals("parameetrid") && küss.toString().contains("&n&")) {
                        for (String i : rida.replace("\n", "").split("&n&")) {
                            variant.add(i);
                            seisund = "vastus";
                                                    }
                    }
                    else {
                        variant.add(rida);
                    }
                }
            }
        }
        return küsimustik;
    }
}