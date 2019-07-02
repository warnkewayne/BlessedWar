package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.store.Coll;

public class aFactionColl extends Coll<aFaction> {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static aFactionColl i = new aFactionColl();
    public static aFactionColl get() { return i; }
    public aFactionColl()
    {
        this.setCleanTaskEnabled(true);
    }

    // -------------------------------------------- //
    // STACK TRACEABILITY
    // -------------------------------------------- //

    @Override
    public void onTick()
    {
        super.onTick();
    }
}
