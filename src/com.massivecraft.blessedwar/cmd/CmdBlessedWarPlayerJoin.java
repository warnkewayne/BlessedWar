package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Align;
import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.Button;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;

import java.util.List;

public class CmdBlessedWarPlayerJoin extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarPlayerJoin()
    {
        this.addParameter(TypeAlignment.get(),"alignment");

        this.addRequirements(RequirementHasPerm.get(Perm.JOIN_LEAVE));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        if(!msender.allowAlignChange()) { msender.msg("<b>You cannot change your alignment so soon!"); return; }

        //Args
        Align align = readArg();
        Alignment alignment = Alignment.get(align.getName());

        Alignment msenderAlignment = msender.getAlignment();

        // Check if player's faction is aligned
        if(msenderFaction.getAlignment() != null)
        {
            msender.msg("<b>Your faction is already aligned with %s", msenderFaction.getAlignment());
            return;
        }

        // Check if player has an alignment
        if(msenderAlignment != null)
        {

            Button btnLeave = new Button().setName("Leave").setSender(sender).setCommand(CmdBlessedWar.get().cmdBlessedWarPlayerLeave);
            msender.message(Mson.parse("<b>You are already aligned with %s", msenderAlignment).add(btnLeave.render()));

            return;
        }


        // Add player to list
        msender.setAlignment(alignment);
        alignment.addPlayer(msender.getId());

        // Sender message
        msender.msg("<i>You have successfully aligned with %s", align);
    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarJoin; }
}

