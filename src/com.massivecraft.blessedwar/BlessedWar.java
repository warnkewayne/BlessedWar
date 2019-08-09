package com.massivecraft.blessedwar;

import com.massivecraft.blessedwar.cmd.CmdBlessedWar;
import com.massivecraft.blessedwar.engine.*;
import com.massivecraft.blessedwar.entity.AlignmentColl;
import com.massivecraft.blessedwar.entity.MConfColl;
import com.massivecraft.blessedwar.entity.MPlayerColl;
import com.massivecraft.blessedwar.entity.AFactionColl;
import com.massivecraft.factions.cmd.CmdFactions;
import com.massivecraft.massivecore.MassivePlugin;

public class BlessedWar extends MassivePlugin {

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static BlessedWar i;
    public static BlessedWar get() { return i; }
    public BlessedWar() { BlessedWar.i = this; }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void onEnableInner()
    {
        //Activate
        this.activate(

                //Coll
                MConfColl.class,
                AlignmentColl.class,
                AFactionColl.class,
                MPlayerColl.class,

                //Engine
                EngineEntityDeath.class,
                EngineFactionJoin.class,
                EngineFactionDisband.class,
                EngineLastActivity.class,
                EnginePlayerData.class,

                //Command
                CmdBlessedWar.class
        );

        CmdFactions.get()
                .addChild(CmdBlessedWar.get().cmdBlessedWarJoinFaction)
                .addChild(CmdBlessedWar.get().cmdBlessedWarLeaveFaction);
    }
}
