package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Modify;
import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.Stat;
import com.massivecraft.blessedwar.cmd.type.TypeAlignment;
import com.massivecraft.blessedwar.cmd.type.TypeModifier;
import com.massivecraft.blessedwar.cmd.type.TypeStatistic;
import com.massivecraft.blessedwar.entity.Alignment;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.MassiveException;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;
import com.massivecraft.massivecore.command.type.primitive.TypeInteger;

import java.util.List;

public class CmdBlessedWarModifyStat extends BlessedWarCommand
{

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWarModifyStat()
    {
        this.addParameter(TypeModifier.get(), "modifier");
        this.addParameter(TypeStatistic.get(), "stat");
        this.addParameter(TypeAlignment.get(), "alignment");
        this.addParameter(TypeInteger.get());

        this.addRequirements(RequirementHasPerm.get(Perm.MODIFY));
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void perform() throws MassiveException
    {
        Enum mod = readArg();
        Enum stat = readArg();
        Alignment align = readArg();
        int change = readArg();
        String a = align.getName();

        // No negatives >:(
        if(change < 0)
        {
            msender.msg("<b>Number cannot be a negative number");
            return;
        }

        if(mod == Modify.ADD)
        {
            if(stat == Stat.POPULATION) { align.addToAlignPop(change); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.PLAYERKILLS) { align.addToAlignPlayerKillCount(change); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.MOBKILLS) { align.addToAlignMobKillCount(change); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.REGALS) { align.addToAlignTotalRegals((double) change); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.TOKENS) { align.addToAlignTokensRedeemed(change); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.BUILDS) { align.addToAlignmentBuilds(change); msender.msg("<i>s% 's has been changed by %s.", a, stat, change); return; }
        }

        if(mod == Modify.SUBTRACT)
        {
            if(stat == Stat.POPULATION) { align.addToAlignPop((change * -1)); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.PLAYERKILLS) { align.addToAlignPlayerKillCount((change * -1)); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.MOBKILLS) { align.addToAlignMobKillCount((change * -1)); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.REGALS) { align.addToAlignTotalRegals((double) (change * -1)); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.TOKENS) { align.addToAlignTokensRedeemed((change * -1)); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }

            if(stat == Stat.BUILDS) { align.addToAlignmentBuilds((change * -1)); msender.msg("<i>%s 's %s has been changed by %s.", a, stat, change); return; }
        }

        if(mod == Modify.SET)
        {
            if(stat == Stat.POPULATION) { align.setAlignmentPopulation(change); msender.msg("<i>%s 's %s has been set to %s.", a, stat, change); return; }

            if(stat == Stat.PLAYERKILLS) { align.setAlignmentPlayerKillCount(change); msender.msg("<i>%s 's %s has been set to %s.", a, stat, change); return; }

            if(stat == Stat.MOBKILLS) { align.setAlignmentMobKillCount(change); msender.msg("<i>%s 's %s has been set to %s.", a, stat, change); return; }

            if(stat == Stat.REGALS) { align.setAlignmentTotalRegals((double) change); msender.msg("<i>%s 's %s has been set to %s.", a, stat, change); return; }

            if(stat == Stat.TOKENS) { align.setAlignmentTokensRedeemed(change); msender.msg("<i>%s 's %s has been set to %s.", a, stat, change); return; }

            if(stat == Stat.BUILDS) { align.setAlignmentBuilds(change); msender.msg("<i>%s 's %s has been set to %s.", a, stat, change); return; }
        }

    }


    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWarModifyStat; }
}
