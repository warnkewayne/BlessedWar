package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.BWPlayer;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.BWFaction;
import com.massivecraft.factions.cmd.FactionsCommand;
import com.massivecraft.factions.entity.MPlayer;
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

        this.addRequirements(RequirementHasPerm.get(Perm.FAC_JOIN));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        // Args
        Alignment alignment = readArg();
        String alignmentId = alignment.getId();

        // Check if player has faction
        if(msenderFaction == null)
        {
            msender.msg("<b>You need to be apart of a faction.");
            return;
        }

        // Check if the player has faction permissions
        if(msender != msenderFaction.getLeader())
        {
            msender.msg("<b>You must be the Leader of %s <b>to align it.", msenderFaction.describeTo(msender));
            return;
        }

        BWFaction afaction = BWFaction.get(msenderFaction);

        if(!afaction.allowAlignChange()) { msender.msg("<b>You cannot change your alignment so soon!"); return; }


        // Check if the faction is already aligned
        if(afaction.getAlignment() != null)
        {
            msender.msg("%s <b>is already aligned with %s", msenderFaction.describeTo(msender), afaction.getAlignment().getName());
            return;
        }

        // Add to list
        alignment.addFaction(afaction.getId());

        // Give Faction alignment
        afaction.setAlignmentId(alignmentId);

            // Get all members of the Faction
        List <MPlayer> members = msenderFaction.getMPlayers();

        for(MPlayer member : members)
        {
            BWPlayer bwPlayer =
                    BWPlayer.get(member.getId());

            bwPlayer.setAlignmentId(alignmentId); // give player religion
            alignment.addPlayer(bwPlayer.getId()); //add to playerlist
        }

        // Change name
        String fName = msenderFaction.getName();
        msenderFaction.setName(alignment.getSymbol() + fName);

        // Send message
        msenderFaction.msg("%s <i>has successfully aligned with %s", msenderFaction.describeTo(msenderFaction), alignment.getName());

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarJoin; }
}
