package de.knacrack.enhanced_survival.utils;

/**
 * Contains messages for Commands
 * @author Knacrack 
 *
 * %p% : Replace for name of Player
 *
 */
public enum Messages {

    PLAYER_NOT_ONLINE(0, "Dieser Spieler ist nicht Online."),
    PLAYER_NOT_EXIST(1, "Dieser Spieler existiert nicht!"),
    ERROR(2, "Ein Fehler ist aufgetreten!"),
    SYNTAX_ERROR(3, "Du hast den Befehl falsch eingegeben"),
    SELF_HEALING(4, "Du hast dich geheilt."),
    OTHER_HEALING(5, "Du Hast %p% geheilt."),
    YOU_CANT_DO_THAT(6, "Du kannst das nicht machen!"),
    NO_PERMISSION(7, "Du hast keine Berechtigung für diese Aktion"),
    PERMISSION_PREFIX(8, "de.enhanced_survival");



    private final int id;

    private String message;



    private Messages(int id, String message) {
        this.id = id;
        this.message = message;
    }



    public int getMessageId() {
        return this.id;
    }



    public String getMessage() {
        return this.message;
    }

}
