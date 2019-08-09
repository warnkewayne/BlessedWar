package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.BWPlayer;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.blessedwar.entity.BWFaction;
import com.massivecraft.factions.cmd.FactionsCommand;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBlessedWarLeaveFaction extends FactionsCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarLeaveFaction()
    {
        this.addRequirements(RequirementHasPerm.get(Perm.FAC_LEAVE));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform()
    {
        // Check if player has faction
        if(msenderFaction == null)
        {
            msender.msg("<b>You need to be apart of a faction.");
            return;
        }

        // Check if the player has faction permissions
        if(msender != msenderFaction.getLeader())
        {
            msender.msg("<b>You must be the Leader of %s to leave an alignment.", msenderFaction.describeTo(msender));
            return;
        }

        BWFaction afac = BWFaction.get(msenderFaction);

        // Check if aligned
        if(afac.getAlignmentId() == null)
        {
            msender.msg("<b>%s is not aligned to any religion.", msenderFaction.describeTo(msender));
            return;
        }

        Alignment alignment = afac.getAlignment();

        // Get all members of the Faction
        List <MPlayer> members = msenderFaction.getMPlayers();

        for(MPlayer member : members)
        {
            BWPlayer bwPlayer =
                    BWPlayer.get(member.getId());

            bwPlayer.leaveAlignment(); //Player entity leaves Alignment
            alignment.removePlayer(bwPlayer.getId()); // Remove player from Alignment's list
        }

        // Change name back
        String fName = msenderFaction.getName();
        fName = fName.replace(alignment.getSymbol(), "");
        msenderFaction.setName(fName);

        // Leave alignment
        alignment.removeFaction(afac.getId());
        afac.setAlignmentId(null);

        msenderFaction.msg("<i>The faction %s <i>left %s", msenderFaction.describeTo(msenderFaction), alignment.getName());
    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarLeave; }

}
