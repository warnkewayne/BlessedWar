package com.massivecraft.blessedwar.entity;


import com.massivecraft.massivecore.store.SenderColl;

public class BWPlayerColl extends SenderColl<BWPlayer> {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static BWPlayerColl i = new BWPlayerColl();
    public static BWPlayerColl get() { return i; }
    public BWPlayerColl()
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

    // -------------------------------------------- //
    // OVERRIDE: COLL
    // -------------------------------------------- //

    @Override
    public long getCleanInactivityToleranceMillis()
    {
        return MConf.get().cleanInactivityToleranceMillis;
    }
}

