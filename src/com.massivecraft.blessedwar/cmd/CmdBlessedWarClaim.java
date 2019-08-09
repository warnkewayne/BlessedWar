package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Align;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.blessedwar.Perm;
import org.bukkit.Bukkit;

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
    public void perform()
    {
        // Check if Player has reward available to claim
        if(!msender.getUnclaimedReward()) { msender.msg("<b>You do not have a reward to claim."); return; }

        // Check if Player's inventory is full ?
        if(msender.getPlayer().getInventory().firstEmpty() == -1)
        { msender.msg("<b>Your inventory is full! Please make a slot to claim your reward."); return; }

        // Place reward in inventory
        // Remove reward from player's data

        String cmd = MConf.get().awardCmdBase.replaceFirst("/", "");
        cmd = cmd + " " + msender.getName() + " ";

        Alignment alignment = Alignment.get(msender.getAlignmentId());

        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                cmd + alignment.getAwardItem() + MConf.get().awardQuantity);



        msender.setUnclaimedReward(false);
    }

    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarClaim; }
}
