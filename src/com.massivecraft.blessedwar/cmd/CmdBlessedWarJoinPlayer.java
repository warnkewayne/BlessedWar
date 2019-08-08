package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.Button;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.mson.Mson;

import java.util.List;

public class CmdBlessedWarJoinPlayer extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarJoinPlayer()
    {
        this.addParameter(TypeAlignment.get(),"alignment");

        this.addRequirements(RequirementHasPerm.get(Perm.JOIN));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        if(!msender.allowAlignChange()) { msender.msg("<b>You cannot change your alignment so soon!"); return; }

        //Args
        Alignment alignment = readArg();

        // Alignment Ids
        String alignmentId = alignment.getId();
        String msenderAlignmentId = msender.getAlignmentId();

        // Msender's alignment
        Alignment msenderAlignment = Alignment.get(msenderAlignmentId);

        // Check if player has an alignment
        if(msenderAlignmentId != null)
        {
            // Check if player's alignment is the same
            if(msenderAlignmentId.equals(alignmentId))
            {
                msender.msg("<i>You are already aligned with %s", alignment.getName());
                return;
            }

            Button btnLeave = new Button().setName("Leave").setSender(sender).setCommand(CmdBlessedWar.get().cmdBlessedWarLeavePlayer);
            msender.message(Mson.parse("<b>You are already aligned with %s", msenderAlignment.getName()).add(btnLeave.render()));

            return;
        }

        // Check if player's faction is aligned
        if(msenderFaction != null && msenderFaction.getAlignment() != null)
        {
            msender.msg("<b>Your faction is already aligned with %s", msenderFaction.getAlignment().getName());
            return;
        }

        // Add player to list
        msender.changedAlignment(alignmentId);
        alignment.addPlayer(msender.getId());

        // Sender message
        msender.msg("<i>You have successfully aligned with %s", alignment.getName());

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarJoin; }
}

