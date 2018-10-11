package net.berrygames.witchrush.team;

import net.berrygames.witchrush.tools.PNJSpawner;
import org.bukkit.Color;

public enum TeamsInfos {

    VERT(0,"Vert", "vert", "§a", (short)13, Color.GREEN),
    BLEU(1,"Bleu", "bleu", "§b", (short)11, Color.BLUE),
    JAUNE(2,"Jaune", "jaune", "§e", (short)4, Color.YELLOW),
    ROUGE(3,"Rouge", "rouge", "§c", (short)14, Color.RED),
    ;

    private int id;
    private String IDName;
    private String teamName;
    private String chatColor;
    private short dataClay;
    private Color color;

    TeamsInfos(int id, String IDName, String teamName, String chatColor, short dataClay, Color color) {
        this.id = id;
        this.IDName = IDName;
        this.teamName = teamName;
        this.chatColor = chatColor;
        this.dataClay = dataClay;
        this.color = color;
    }

    public String getIDName() {
        return IDName;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getChatColor() {
        return chatColor;
    }

    public short getDataClay() {
        return dataClay;
    }

    public Color getColor() {
        return color;
    }

    public int getId() {
        return id;
    }

    public static TeamsInfos getTeamInfosByIDName(final String ID) {
        for (final TeamsInfos teamInfos : values()) {
            if (teamInfos.getIDName().equalsIgnoreCase(ID)) {
                return teamInfos;
            }
        }
        return null;
    }

    public static TeamsInfos getTeamInfosByID(final int ID) {
        for (final TeamsInfos teamInfos : values()) {
            if (teamInfos.getId() == ID) {
                return teamInfos;
            }
        }
        return null;
    }

    public static TeamsInfos getTeamInfosByShortData(final short data) {
        for (final TeamsInfos teamInfos : values()) {
            if (teamInfos.getDataClay() == data) {
                return teamInfos;
            }
        }
        return null;
    }

}
