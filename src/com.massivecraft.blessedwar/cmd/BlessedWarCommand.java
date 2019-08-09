package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.entity.BWPlayer;
import com.massivecraft.blessedwar.entity.BWFaction;
import com.massivecraft.massivecore.command.MassiveCommand;


public class BlessedWarCommand extends MassiveCommand {

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    public BWPlayer msender;
    public BWFaction msenderFaction;

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public BlessedWarCommand() { this.setSetupEnabled(true); }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void senderFields(boolean set)
    {
        this.msender = set ? BWPlayer.get(sender) : null;
        this.msenderFaction = set ? this.msender.getBWFaction() : null;
    }

}
