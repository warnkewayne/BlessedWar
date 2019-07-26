package com.massivecraft.blessedwar;

import com.massivecraft.blessedwar.cmd.CmdBlessedWar;
import com.massivecraft.blessedwar.cmd.CmdBlessedWarJoinFaction;
import com.massivecraft.blessedwar.cmd.CmdBlessedWarLeaveFaction;
import com.massivecraft.blessedwar.engine.EngineEntityDeath;
import com.massivecraft.blessedwar.entity.AlignmentColl;
import com.massivecraft.blessedwar.entity.MConfColl;
import com.massivecraft.blessedwar.entity.MPlayerColl;
import com.massivecraft.blessedwar.entity.aFactionColl;
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
                aFactionColl.class,
                AlignmentColl.class,
                MPlayerColl.class,
                MConfColl.class,

                //Engine
                EngineEntityDeath.class,
                //EngineFactionJoin.class,

                //Command
                CmdBlessedWar.class,
                CmdFactions.get()
                        .addChild(CmdBlessedWar.get().cmdBlessedWarJoinFaction)
                        .addChild(CmdBlessedWar.get().cmdBlessedWarLeaveFaction)


        );

    }
}
