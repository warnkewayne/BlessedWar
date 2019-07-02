package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.command.editor.annotation.EditorVisible;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

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
    // OVERRIDE
    // -------------------------------------------- //


    // -------------------------------------------- //
    // BLESSEDWAR ACTIVE
    // -------------------------------------------- //
    @EditorVisible(false)
    public static boolean blessedWarActive = false;

    // -------------------------------------------- //
    // AWARD ITEMS
    // -------------------------------------------- //
    // public static ItemStack awardItemUnionism = ;
    // public static ItemStack awardItemDragon = ;
    // public static ItemStack awardItemVoid = ;
    // public static ItemStack awardItemEstel = ;

    // -------------------------------------------- //
    // AWARD FACTIONS REGALS
    // -------------------------------------------- //
    public static boolean facRegalAward = false; // Defaulted to false.
    public static double facRegalAwardAmount = 0; // Defaulted to 0.

    // -------------------------------------------- //
    // ALIGNMENT CHANGE COOLDOWN
    // -------------------------------------------- //
    public static long alignChangeCooldown = 30; //30 days

    // -------------------------------------------- //
    // REWARD CLAIM / ALIGNMENT CHANGE
    // -------------------------------------------- //
    // If a player joins a faction, that is winning,
    // mid 'blessed war season, then we will not
    // allow them to claim the award that this faction would
    // get after this amount of time.

    public static long rewardClaimCooldown = 15; // 15 days in Millis

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
