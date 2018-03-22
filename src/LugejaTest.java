import java.util.ArrayList;

// Klass faililugeja testimiseks, pole rakenduse tööks vajalik
public class LugejaTest {
    public static void main(String[] args) throws Exception{
        Lugeja logistik = new Lugeja();
        ArrayList<Küsimus> alfa = logistik.loe();

        for (Küsimus i: alfa) {
            System.out.println(i.getKüsimus());
            for (ArrayList<String> j: i.getVäärtused()) {
                for (String k: j) {
                    System.out.println(k);
                }
            }
        }
    }
}