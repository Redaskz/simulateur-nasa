// Exception metier utilisee quand une regle de lancement n'est pas respectee.
public class CarburantInsuffisantException extends Exception {
    public CarburantInsuffisantException(String message) {
        super(message);
    }
}
