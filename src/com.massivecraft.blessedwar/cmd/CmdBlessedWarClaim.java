package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.blessedwar.Perm;

import java.util.List;

public class CmdBlessedWarClaim extends BlessedWarCommand {

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarClaim()
    {
        this.addRequirements(RequirementHasPerm.get(Perm.CLAIM));
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        // Check if Player has reward available to claim
        if(!msender.getUnclaimedReward()) { msender.msg("<b>You do not have a reward to claim."); return; }

        // Check if Player's inventory is full ?
        if(msender.getPlayer().getInventory().firstEmpty() == -1)
        { msender.msg("<b>Your inventory is full! Please make a slot to claim your reward."); return; }

        // Check Unclaimed PLAYER reward && Check factionPlayerClaimed List
        if(!msender.getUnclaimedReward() || msender.getaFaction().getPlayersClaimed().contains(msender))
        { msender.msg("<b>You do not have reward to claim!"); return; }


        //TODO:
        // Place reward in inventory
        // Remove reward from player's data

    }

    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarClaim; }
}
