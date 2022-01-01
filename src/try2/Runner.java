package try2;
import java.io.*;

public class Runner implements Serializable {

    private static final long serialVersionUID = -7422177151823659507L;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Reception reception = new Reception();
        reception.mainChoice();

    }
}
