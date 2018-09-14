package net.berrygames.witchrush.team;

public enum TeamInfos {

    VERT("Vert", "Vert", "§a", (short)13),
    BLEU("Bleu", "Bleu", "§b", (short)11),
    JAUNE("Jaune", "Jaune", "§e", (short)4),
    ROUGE("Rouge", "Rouge", "§c", (short)14),
    ;

    private String IDName;
    private String teamName;
    private String chatColor;
    private short dataClay;

    TeamInfos(String IDName, String teamName, String chatColor, short dataClay) {
        this.IDName = IDName;
        this.teamName = teamName;
        this.chatColor = chatColor;
        this.dataClay = dataClay;
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
