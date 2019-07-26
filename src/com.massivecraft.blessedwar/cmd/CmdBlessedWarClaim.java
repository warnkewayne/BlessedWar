package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
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
    public void perform() throws MassiveException
    {
        // Check if Player has reward available to claim
        if(!msender.getUnclaimedReward()) { msender.msg("<b>You do not have a reward to claim."); return; }

        // Check if Player's inventory is full ?
        if(msender.getPlayer().getInventory().firstEmpty() == -1)
        { msender.msg("<b>Your inventory is full! Please make a slot to claim your reward."); return; }

        //TODO:
        // Check Unclaimed PLAYER reward && Check factionPlayerClaimed List
        //if(!msender.getUnclaimedReward() || msender.getaFaction().getPlayersClaimed().contains(msender))
        //{ msender.msg("<b>You do not have reward to claim!"); return; }

        // Place reward in inventory
        // Remove reward from player's data

        String cmd = MConf.get().awardCmdBase.replaceFirst("/", "");
        cmd = cmd + " " + msender.getName() + " ";

        switch(msender.getAlignment().getId())
        {
            case Alignment.ID_UNIONISM:
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                        cmd + MConf.get().awardItemUnionism + MConf.get().awardQuantity);
                break;

            case Alignment.ID_DRAGON:
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                        cmd + MConf.get().awardItemDragon + MConf.get().awardQuantity);
                break;

            case Alignment.ID_ESTEL:
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                        cmd + MConf.get().awardItemEstel + MConf.get().awardQuantity);
                break;

            case Alignment.ID_VOID:
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),
                        cmd + MConf.get().awardItemVoid + MConf.get().awardQuantity);
                break;

            default:
                msender.msg("<b>You do not have a reward to claim."); return;
        }

        msender.setUnclaimedReward(false);
    }

    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarClaim; }
}
