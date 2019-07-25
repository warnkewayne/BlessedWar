package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Align;
import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.aFaction;
import com.massivecraft.factions.cmd.FactionsCommand;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.blessedwar.Perm;

import java.util.List;

public class CmdBlessedWarJoinFaction extends FactionsCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarJoinFaction()
    {
        this.addParameter(TypeAlignment.get(), "alignment");

        this.addRequirements(RequirementHasPerm.get(Perm.FAC_JOIN_LEAVE));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        if(!aFaction.get(msenderFaction).allowAlignChange()) { msender.msg("<b>You cannot change your alignment so soon!"); return; }

        // Args
        Align align = readArg();
        Alignment alignment = Alignment.getFromAlign(align);



        // Check if player has faction
        if(msenderFaction == null)
        {
            msender.msg("<b>You need to be apart of a faction.");
            return;
        }

        // Check if the player has faction permissions
        if(msender != msenderFaction.getLeader())
        {
            msender.msg("<b>You must be the Leader of %s to align it.", msenderFaction.describeTo(msender));
            return;
        }

        // Check if the faction is already aligned
        if(alignment.isFactionAligned(msenderFaction.getId()))
        {
            msender.msg("<b>%s is already aligned with %s", msenderFaction.describeTo(msender), alignment.getName());
            return;
        }

        // Add to list
        alignment.addFaction(msenderFaction.getId());

        // Give Faction alignment
        aFaction.get(msenderFaction).setAlignmentId(alignment.getId());
        msenderFaction.setName(alignment.getSymbol());

        // Send message
        msender.msg("<i>%s has successfully aligned with %s", msenderFaction, alignment.getName());


    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarJoin; }
}
