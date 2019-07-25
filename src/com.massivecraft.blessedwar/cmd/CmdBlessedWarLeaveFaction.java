package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.aFaction;
import com.massivecraft.factions.cmd.FactionsCommand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBlessedWarLeaveFaction extends FactionsCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarLeaveFaction()
    {
        this.addRequirements(RequirementHasPerm.get(Perm.FAC_JOIN_LEAVE));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        // Check if player has faction
        if(msenderFaction == null)
        {
            msender.msg("<b>You be apart of a faction.");
            return;
        }

        // Check if the player has faction permissions
        if(msender != msenderFaction.getLeader())
        {
            msender.msg("<b>You must be the Leader of %s to leave an alignment.", msenderFaction.describeTo(msender));
            return;
        }

        // Check if aligned
        if(aFaction.get(msenderFaction).getAlignmentId() == null)
        {
            msender.msg("<b>%s is not aligned to any religion.", msenderFaction.describeTo(msender));
            return;
        }

        aFaction afac = aFaction.get(msenderFaction);
        Alignment alignment = afac.getAlignment();

        // Leave alignment
        alignment.removeFaction(afac.getId());
        afac.setAlignmentId(null);
        afac.emptyPlayersClaimed();

        //TODO:
        // Send message to faction

        msenderFaction.msg("The faction %s left %s", msenderFaction.describeTo(msenderFaction), alignment);
    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarLeave; }

}
