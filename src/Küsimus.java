import java.util.ArrayList;

public class Küsimus {
    private String küsimus;
    private ArrayList<ArrayList<String>> väärtused;

    public ArrayList<ArrayList<String>> getVäärtused() {
        return väärtused;
    }

    public String getKüsimus() {
        return küsimus;
    }

    public Küsimus(String küsimus, ArrayList<ArrayList<String>> väärtused) {
        this.küsimus = küsimus;
        this.väärtused = väärtused;
    }

    String Kontrolli(String[] väärtustus) {
        for (ArrayList<String> j:väärtused) {
            for (int i = 0; i < väärtustus.length; i++) {
                if (!j.get(i).equals(väärtustus[i])) {
                    break;
                }
                if (i == väärtustus.length - 1) {
                    return j.get(j.size()-1);
                }
            }
        }
        return "";
    }
}