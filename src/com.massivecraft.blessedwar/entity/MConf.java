package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.command.editor.annotation.EditorType;
import com.massivecraft.massivecore.command.editor.annotation.EditorVisible;
import com.massivecraft.massivecore.command.type.TypeMillisDiff;
import com.massivecraft.massivecore.command.type.TypeStringCommand;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.TimeUnit;

import java.util.List;

@EditorName("config")
public class MConf extends Entity<MConf>
{
    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    protected static transient MConf i;
    public static MConf get() { return i; }

    // -------------------------------------------- //
    // BLESSEDWAR ACTIVE
    // -------------------------------------------- //
    @EditorVisible(false)
    public boolean blessedWarActive = false;

    // -------------------------------------------- //
    // BLESSEDWAR STARTING QUEST NODES
    // -------------------------------------------- //
    public String startNodeUnionism = "essalonia.blessedwar.unionism";
    public String startNodeEstel = "essalonia.blessedwar.estel";
    public String startNodeDragon = "essalonia.blessedwar.dragon";
    public String startNodeVoid = "essalonia.blessedwar.void";

    // -------------------------------------------- //
    // AWARD ITEMS
    // -------------------------------------------- //

    public int awardQuantity = 1; // default to 1

    @EditorType(TypeStringCommand.class)
    public String awardCmdBase = "/crate key";
    public String awardItemUnionism = "unionismkey";
    public String awardItemEstel = "estelkey";
    public String awardItemDragon = "dragonkey";
    public String awardItemVoid = "voidkey";

    // -------------------------------------------- //
    // AWARD FACTIONS REGALS
    // -------------------------------------------- //
    public boolean facRegalAward = false; // Defaulted to false.
    public double facRegalAwardAmount = 0; // Defaulted to 0.

    // -------------------------------------------- //
    // ALIGNMENT CHANGE COOLDOWN
    // -------------------------------------------- //
    @EditorType(TypeMillisDiff.class)
    public long alignChangeCooldown = 30 * TimeUnit.MILLIS_PER_DAY; //30 days

    // -------------------------------------------- //
    // REWARD CLAIM / ALIGNMENT CHANGE
    // -------------------------------------------- //
    // If a player joins a faction, that is winning,
    // mid 'blessed war season, then we will not
    // allow them to claim the award that this faction would
    // get after this amount of time.

    @EditorType(TypeMillisDiff.class)
    public long rewardClaimCooldown = 15 * TimeUnit.MILLIS_PER_DAY; // 15 days

    // -------------------------------------------- //
    // COMMAND ALIASES
    // -------------------------------------------- //

    public List<String> aliasesBlessedWar = MUtil.list("bw");
    public List<String> aliasesBlessedWarConfig = MUtil.list("config", "c");
    public List<String> aliasesBlessedWarVersion = MUtil.list("version", "v");
    public List<String> aliasesBlessedWarAward = MUtil.list("award");
    public List<String> aliasesBlessedWarJoin = MUtil.list("align");
    public List<String> aliasesBlessedWarLeave = MUtil.list("unalign");
    public List<String> aliasesBlessedWarEnd = MUtil.list("end");
    public List<String> aliasesBlessedWarStart = MUtil.list("start");
    public List<String> aliasesBlessedWarModifyStat = MUtil.list("modifystat");
    public List<String> aliasesBlessedWarReport = MUtil.list("report");
    public List<String> aliasesBlessedWarClaim = MUtil.list("claim");

}
