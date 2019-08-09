package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.command.editor.annotation.EditorName;
import com.massivecraft.massivecore.command.editor.annotation.EditorType;
import com.massivecraft.massivecore.command.editor.annotation.EditorTypeInner;
import com.massivecraft.massivecore.command.editor.annotation.EditorVisible;
import com.massivecraft.massivecore.command.type.TypeMillisDiff;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;
import com.massivecraft.massivecore.util.TimeUnit;

import java.util.List;
import java.util.Map;

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
    // AWARD FACTIONS REGALS
    // -------------------------------------------- //
    public boolean facRegalAward = false; // Defaulted to false.
    public long facRegalAwardAmount = 0; // Defaulted to 0.

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
    // REMOVE DATA
    // -------------------------------------------- //

    public boolean removePlayerWhenBanned = true;

    @EditorType(TypeMillisDiff.class)
    public long cleanInactivityToleranceMillis = 10 * TimeUnit.MILLIS_PER_DAY; //10 Days

    // Player Age Bonus
    @EditorTypeInner({TypeMillisDiff.class, TypeMillisDiff.class})
    public Map<Long, Long> cleanInactivityToleranceMillisPlayerAgeToBonus = MUtil.map(
            2 * TimeUnit.MILLIS_PER_WEEK, 10 * TimeUnit.MILLIS_PER_DAY  // +10 days after 2 weeks
    );

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
