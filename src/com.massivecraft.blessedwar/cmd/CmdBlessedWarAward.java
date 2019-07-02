package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBlessedWarAward extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarAward()
    {
        this.addParameter(TypeAlignment.get(), "alignment");

        this.addRequirements(RequirementHasPerm.get(Perm.AWARD));
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        // The Alignment won! Staff can award them now with keys!
        // A whole ton of keyssss!
        // Read in the alignment, find that alignment...
        // And get the players/factions that fight for that religion ...
        // give every player keys with a message.
        // Factions get monetary value if activated.
        //
        // If these players are offline, award them on next login.


        //TODO:

        //Args
        Alignment align = this.readArg();

        

    }

    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarAward; }
}
