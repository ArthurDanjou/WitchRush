package net.berrygames.witchrush.team;

import org.bukkit.Color;

public enum TeamInfos {

    VERT("Vert", "Vert", "§a", (short)13, Color.GREEN),
    BLEU("Bleu", "Bleu", "§b", (short)11, Color.BLUE),
    JAUNE("Jaune", "Jaune", "§e", (short)4, Color.YELLOW),
    ROUGE("Rouge", "Rouge", "§c", (short)14, Color.RED),
    ;

    private String IDName;
    private String teamName;
    private String chatColor;
    private short dataClay;
    private Color color;

    TeamInfos(String IDName, String teamName, String chatColor, short dataClay, Color color) {
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

    public static TeamInfos getTeamInfosByIDName(final String ID) {
        for (final TeamInfos teamInfos : values()) {
            if (teamInfos.getIDName().equalsIgnoreCase(ID)) {
                return teamInfos;
            }
        }
        return null;
    }

    public static TeamInfos getTeamInfosByShortData(final short data) {
        for (final TeamInfos teamInfos : values()) {
            if (teamInfos.getDataClay() == data) {
                return teamInfos;
            }
        }
        return null;
    }
}
