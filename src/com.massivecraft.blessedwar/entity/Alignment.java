package com.massivecraft.blessedwar.entity;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

import java.util.List;


public class Alignment extends Entity<Alignment> {

    // -------------------------------------------- //
    // CONSTANTS
    // -------------------------------------------- //

    public final static transient String ID_UNIONISM = "unionism";
    public final static transient String ID_ESTEL = "faithofestel";
    public final static transient String ID_DRAGON = "dragonworship";
    public final static transient String ID_VOID = "voidworship";

    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    public static Alignment get(Object oid) { return AlignmentColl.get().get(oid); }

    //----------------------------------------------//
    // OVERRIDE
    //----------------------------------------------//

    @Override
    public Alignment load(Alignment that)
    {
        this.alignmentName = that.alignmentName;
        this.alignmentSymbol = that.alignmentSymbol;
        this.startingNode = that.startingNode;
        this.factionList = that.factionList;
        this.playerList = that.playerList;

        this.alignmentPopulation = that.alignmentPopulation;
        this.alignmentPlayerKillCount = that.alignmentPlayerKillCount;
        this.alignmentMobKillCount = that.alignmentMobKillCount;
        this.alignmentTokensRedeemed = that.alignmentTokensRedeemed;
        this.alignmentTotalRegals = that.alignmentTotalRegals;

        return this;
    }

    // -------------------------------------------- //
    // FIELDS: RAW
    // -------------------------------------------- //

    // This is the name of the Alignment.
    // Default: ""

    private String alignmentName = "";

    // Symbol for the Alignment.
    // Default: ""

    private String alignmentSymbol = "";

    // List of factions that are apart of this Alignment
    // Default: Empty {}

    private String startingNode = "";

    // Starting Quest Node for this Alignment
    // Default: ""

    private List<String> factionList = MUtil.list();

    //TODO: might not be necessary
    // List of INDIVIDUAL players that are apart of this Alignment
    // Default: Empty {}

    private List<String> playerList = MUtil.list();

    // The following are Blessed War Stats

    // This is the population currently in the
    // Religion
    // Default: 0

    private int alignmentPopulation = 0;

    // This is the player count killed by
    // this alignment
    // Default 0

    private int alignmentPlayerKillCount = 0;

    // This is the mob count killed by
    // this alignment
    // Default 0

    private int alignmentMobKillCount = 0;

    // This is the tokens redeemed
    // by this alignment
    // Default 0

    private int alignmentTokensRedeemed = 0;

    // This is the total regals
    // the alignment has
    // Default 0

    private double alignmentTotalRegals = 0;

    // -------------------------------------------- //
    // CORE UTILITIES
    // -------------------------------------------- //

    public void emptyFactionPlayerLists()
    {
        emptyFactionList();
        emptyPlayerList();
    }

    public void printStats(MPlayer msender)
    {
        msender.msg("------------------------------------");
        msender.msg("<i>%s %s Stats: ", this.alignmentName, this.alignmentSymbol);
        msender.msg("------------------------------------");
        msender.msg("%s players", this.alignmentPopulation);
        msender.msg("%s players killed", this.alignmentPlayerKillCount);
        msender.msg("%s mobs killed", this.alignmentMobKillCount);
        msender.msg("%s regals earned", this.alignmentTotalRegals);
        msender.msg("%s tokens earned", this.alignmentTokensRedeemed);
        msender.msg("------------------------------------");
        msender.msg("");
    }

    public void resetAlignmentStats()
    {
        // Population is not reset
        this.alignmentPlayerKillCount = 0;
        this.alignmentMobKillCount = 0;
        this.alignmentTokensRedeemed = 0;
        //TODO: What do with totalRegals?

        this.changed();
    }

    // -------------------------------------------- //
    // FIELD: alignmentName
    // -------------------------------------------- //

    public void setName(String aname)
    {

        if(MUtil.equals(aname, this.alignmentName)) return;

        this.alignmentName = aname;
        this.changed();
    }

    public String getName() { return this.alignmentName; }

    // -------------------------------------------- //
    // FIELD: alignmentSymbol
    // -------------------------------------------- //

    public void setSymbol(String symbol)
    {
        if(MUtil.equals(symbol, this.alignmentSymbol)) return;

        this.alignmentSymbol = symbol;
        this.changed();
    }

    public String getSymbol() { return this.alignmentSymbol; }

    // -------------------------------------------- //
    // FIELD: startingNode
    // -------------------------------------------- //

    public void setStartingNode(String startingNode)
    {
        if(MUtil.equals(startingNode, this.startingNode)) return;

        this.startingNode = startingNode;
        this.changed();
    }

    public String getStartingNode() { return this.startingNode; }

    // -------------------------------------------- //
    // FIELD: factionList
    // -------------------------------------------- //

    public void addFaction(String factionId)
    {
        if(factionList.contains(factionId)) return;

        alignmentPopulation = alignmentPopulation +
                Faction.get(factionId).getMPlayers().size();

        factionList.add(factionId);
        this.changed();
    }

    public void removeFaction(String factionId)
    {
        if(! factionList.contains(factionId)) return;

        alignmentPopulation = alignmentPopulation -
                Faction.get(factionId).getMPlayers().size();

        factionList.remove(factionId);
        this.changed();
    }

    public boolean isFactionAligned(String factionId)
    {
        return factionList.contains(factionId);
    }

    public void emptyFactionList()
    {
        //TODO: Please don't do this.
        for(String facId : factionList)
        {
            alignmentPopulation = alignmentPopulation - FactionColl.get().get(facId).getMPlayers().size();
        }

        factionList.clear();
        this.changed();
    }

    // -------------------------------------------- //
    // FIELD: playerList
    // -------------------------------------------- //

    public List<String> getPlayerList()
    {
        return this.playerList;
    }

    public void addPlayer(String playerId)
    {
        if(playerList.contains(playerId)) return;

        alignmentPopulation++;

        playerList.add(playerId);
        this.changed();
    }

    public void removePlayer(String playerId)
    {
        if(! playerList.contains(playerId)) return;

        alignmentPopulation--;

        playerList.remove(playerId);
        this.changed();
    }

    public boolean isPlayerAligned(String playerId)
    {
        return factionList.contains(playerId);
    }

    public void emptyPlayerList()
    {
        alignmentPopulation = alignmentPopulation - playerList.size();

        playerList.clear();
        this.changed();
    }


    // -------------------------------------------- //
    // FIELD: alignmentPopulation
    // -------------------------------------------- //

    public void setAlignmentPopulation(int pop)
    {
        this.alignmentPopulation = pop;
        this.changed();
    }

    public int getAlignmentPopulation()
    {
        return this.alignmentPopulation;
    }


    public void addToAlignPop(int add)
    {
        this.alignmentPopulation = this.alignmentPopulation + add;
        this.changed();
    }


    // -------------------------------------------- //
    // FIELD: alignmentPlayerKillCount
    // -------------------------------------------- //

    public void setAlignmentPlayerKillCount(int killCount)
    {
        this.alignmentPlayerKillCount = killCount;
        this.changed();
    }

    public int getAlignmentPlayerKillCount()
    {
        return this.alignmentPlayerKillCount;
    }

    public void addToAlignPlayerKillCount(int killCount)
    {
        this.alignmentPlayerKillCount = this.alignmentPlayerKillCount + killCount;
        this.changed();
    }

    // -------------------------------------------- //
    // FIELD: alignmentMobKillCount
    // -------------------------------------------- //

    public void setAlignmentMobKillCount(int killCount)
    {
        this.alignmentMobKillCount = killCount;
        this.changed();
    }

    public int getAlignmentMobKillCount()
    {
        return this.alignmentMobKillCount;
    }

    public void addToAlignMobKillCount(int killCount)
    {
        this.alignmentMobKillCount = this.alignmentMobKillCount + killCount;
        this.changed();
    }

    // -------------------------------------------- //
    // FIELD: alignmentTokensRedeemed
    // -------------------------------------------- //

    public void setAlignmentTokensRedeemed(int tokens)
    {
        this.alignmentTokensRedeemed = tokens;
        this.changed();
    }

    public int getAlignmentTokensRedeemed()
    {
        return this.alignmentTokensRedeemed;
    }

    public void addToAlignTokensRedeemed(int tokens)
    {
        this.alignmentTokensRedeemed = this.alignmentTokensRedeemed + tokens;
        this.changed();
    }

    // -------------------------------------------- //
    // FIELD: alignmentTotalRegals
    // -------------------------------------------- //

    public void setAlignmentTotalRegals(double regals)
    {
        this.alignmentTotalRegals = regals;
        this.changed();
    }

    public double getAlignmentTotalRegals()
    {
        return this.alignmentTotalRegals;
    }

    public void addToAlignTotalRegals(double regals)
    {
        this.alignmentTotalRegals = this.alignmentTotalRegals + regals;
        this.changed();
    }

}
