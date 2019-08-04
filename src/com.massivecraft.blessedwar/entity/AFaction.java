package com.massivecraft.blessedwar.entity;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.store.Entity;

public class AFaction extends Entity<AFaction> {

    // -------------------------------------------- //
    // META
    // -------------------------------------------- //

    public static AFaction get(Faction faction) { return AFactionColl.get().get(faction.getId(), true); }

    //----------------------------------------------//
    // OVERRIDE
    //----------------------------------------------//

    @Override
    public AFaction load(AFaction that)
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
