package net.berrygames.witchrush.team;

import org.bukkit.Color;
import org.bukkit.DyeColor;

public enum TeamsInfos {

    VERT(0,"Vert", "vert", "§a", DyeColor.LIME, (short)5, Color.GREEN, 23),
    BLEU(1,"Bleu", "bleu", "§b", DyeColor.LIGHT_BLUE, (short)3, Color.BLUE, 19),
    JAUNE(2,"Jaune", "jaune", "§e", DyeColor.YELLOW, (short)4, Color.YELLOW, 21),
    ROUGE(3,"Rouge", "rouge", "§c", DyeColor.RED, (short)14, Color.RED, 25),
    ;

    private int id;
    private String teamName;
    private String configName;
    private String chatColor;
    private short dataClay;
    private Color color;
    private DyeColor dyeColor;
    private int slotGUI;

    TeamsInfos(int id, String teamName, String configName, String chatColor, DyeColor dyeColor, short dataClay, Color color, int slotGUI) {
        this.id = id;
        this.teamName = teamName;
        this.configName = configName;
        this.chatColor = chatColor;
        this.dyeColor = dyeColor;
        this.dataClay = dataClay;
        this.color = color;
        this.slotGUI = slotGUI;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getChatColor() {
        return chatColor;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }

    public short getDataClay() {
        return dataClay;
    }

    public Color getColor() {
        return color;
    }

    public String getConfigName() {
        return configName;
    }

    public int getId() {
        return id;
    }

    public int getSlotGUI() {
        return slotGUI;
    }

    public static TeamsInfos getTeamInfosByName(final String name) {
        for (final TeamsInfos teamInfos : values()) {
            if (teamInfos.getTeamName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
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
