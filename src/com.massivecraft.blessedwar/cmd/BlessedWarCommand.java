package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.entity.MPlayer;
import com.massivecraft.blessedwar.entity.AFaction;
import com.massivecraft.massivecore.command.MassiveCommand;


public class BlessedWarCommand extends MassiveCommand {

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    public MPlayer msender;
    public AFaction msenderFaction;

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
        this.msender = set ? MPlayer.get(sender) : null;
        this.msenderFaction = set ? this.msender.getaFaction() : null;
    }

}
