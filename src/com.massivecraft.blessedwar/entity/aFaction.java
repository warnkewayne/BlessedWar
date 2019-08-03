package com.massivecraft.blessedwar.entity;
import com.massivecraft.blessedwar.Align;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.store.Entity;
import com.massivecraft.massivecore.util.MUtil;

import java.util.List;

public class aFaction extends Entity<aFaction> {

    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    public static aFaction get(Faction faction) { return aFactionColl.get().get(faction.getId(), true); }

    //----------------------------------------------//
    // OVERRIDE
    //----------------------------------------------//

    @Override
    public aFaction load(aFaction that)
    {
        this.alignmentId = that.alignmentId;
        this.lastAlignChange = that.lastAlignChange;

        return this;
    }

    // -------------------------------------------- //
    // CORE METHODS
    // -------------------------------------------- //

    public Alignment getAlignment()
    {
        return Alignment.get(this.alignmentId);
    }

    // -------------------------------------------- //
    // FIELDS: RAW
    // -------------------------------------------- //

    // This is the field that we will use to "extend"
    // the Faction.
    // Default null

    private String factionId = null;

    // This is the alignment the faction has chosen.
    // It is a foreign key.
    // Default: null

    private String alignmentId = null;

    // This is the cooldown timer.
    // Alignments are only allowed to switch
    // once a week.
    // Default: currentTime

    private double lastAlignChange = (double) System.currentTimeMillis();


    // -------------------------------------------- //
    // FIELDS: Faction
    // -------------------------------------------- //

    public void setFactionId(String factionId)
    {
        this.factionId = factionId; this.changed();
    }

    public Faction getFaction()
    {
        if(this.factionId != null) return Faction.get(this.factionId);

        return null;
    }

    // -------------------------------------------- //
    // FIELDS: alignmentId
    // -------------------------------------------- //

    public void setAlignmentId(String alignmentId)
    {
        if(this.alignmentId == alignmentId) return;

        this.alignmentId = alignmentId;
        setLastAlignChange((double) System.currentTimeMillis());
        this.changed();
    }

    public String getAlignmentId()
    {
        return this.alignmentId;
    }


    // -------------------------------------------- //
    // FIELDS: alignCooldown
    // -------------------------------------------- //

    public void setLastAlignChange(double lastAlignChange)
    {
        this.lastAlignChange = lastAlignChange;
        this.changed();
    }

    public double getLastAlignChange()
    {
        return this.lastAlignChange;
    }

    public boolean allowAlignChange()
    {
        double timeDiff = System.currentTimeMillis() - this.lastAlignChange;

        // if they changed alignment within a month do not allow.
        return !(timeDiff < MConf.get().alignChangeCooldown);
    }

}
