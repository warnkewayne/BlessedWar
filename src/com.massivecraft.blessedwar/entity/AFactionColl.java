package com.massivecraft.blessedwar.entity;

import com.massivecraft.massivecore.store.Coll;

public class AFactionColl extends Coll<AFaction> {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static AFactionColl i = new AFactionColl();
    public static AFactionColl get() { return i; }
    public AFactionColl()
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
