package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.store.Coll;

public class BWFactionColl extends Coll<BWFaction> {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static BWFactionColl i = new BWFactionColl();
    public static BWFactionColl get() { return i; }
    public BWFactionColl()
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
