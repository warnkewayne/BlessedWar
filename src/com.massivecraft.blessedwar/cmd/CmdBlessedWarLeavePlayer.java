package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBlessedWarLeavePlayer extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarLeavePlayer()
    {

        this.addRequirements(RequirementHasPerm.get(Perm.LEAVE));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform()
    {
        String align = msender.getAlignmentId();

        //Check if player has an alignment
        if(align == null)
        {
            msender.msg("<b>You do not have any alignments");
            return;
        }

        //Check if player's faction has alignment
        if(msenderFaction != null && msenderFaction.getAlignment() != null)
        {
            msender.msg("<b>You cannot abandon your faction's alignment.");
            return;
        }

        // Leave that alignment and clear Player's data
        Alignment alignment = Alignment.get(align);

        alignment.removePlayer(msender.getId());
        msender.leaveAlignment();

        // Send message
        msender.msg("<b>You have left the Blessed War and %s.", alignment.getName());

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarLeave; }
}
