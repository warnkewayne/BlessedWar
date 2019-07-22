package com.massivecraft.blessedwar.cmd;

import com.massivecraft.blessedwar.Perm;
import com.massivecraft.blessedwar.entity.MConf;
import com.massivecraft.massivecore.command.MassiveCommand;
import com.massivecraft.massivecore.command.requirement.RequirementHasPerm;

import java.util.List;

public class CmdBlessedWar extends MassiveCommand {

    // -------------------------------------------- //
    // INSTANCE
    // -------------------------------------------- //

    private static CmdBlessedWar i = new CmdBlessedWar();
    public static CmdBlessedWar get() { return i; }

    // -------------------------------------------- //
    // FIELDS
    // -------------------------------------------- //

    public CmdBlessedWarAward cmdBlessedWarAward = new CmdBlessedWarAward();
    public CmdBlessedWarClaim cmdBlessedWarClaim = new CmdBlessedWarClaim();
    public CmdBlessedWarEnd cmdBlessedWarEnd = new CmdBlessedWarEnd();
    //TODO: active these commands through Factions
    // public CmdBlessedWarFactionJoin cmdBlessedWarFactionJoin = new CmdBlessedWarFactionJoin();
    // public CmdBlessedWarFactionLeave cmdBlessedWarFactionLeave = new CmdBlessedWarFactionLeave();
    public CmdBlessedWarModifyStat cmdBlessedWarModifyStat = new CmdBlessedWarModifyStat();
    public CmdBlessedWarPlayerJoin cmdBlessedWarPlayerJoin = new CmdBlessedWarPlayerJoin();
    public CmdBlessedWarPlayerLeave cmdBlessedWarPlayerLeave = new CmdBlessedWarPlayerLeave();
    public CmdBlessedWarReport cmdBlessedWarReport = new CmdBlessedWarReport();
    public CmdBlessedWarStart cmdBlessedWarStart = new CmdBlessedWarStart();
    public CmdBlessedWarConfig cmdBlessedWarConfig = new CmdBlessedWarConfig();
    public CmdBlessedWarVersion cmdBlessedWarVersion = new CmdBlessedWarVersion();

    // -------------------------------------------- //
    // CONSTRUCT
    // -------------------------------------------- //

    public CmdBlessedWar()
    {
        //Children
        this.addChild(this.cmdBlessedWarAward);
        this.addChild(this.cmdBlessedWarClaim);
        this.addChild(this.cmdBlessedWarEnd);
        //this.addChild(this.cmdBlessedWarFactionJoin);
        //this.addChild(this.cmdBlessedWarFactionLeave);
        this.addChild(this.cmdBlessedWarModifyStat);
        this.addChild(this.cmdBlessedWarPlayerJoin);
        this.addChild(this.cmdBlessedWarPlayerLeave);
        this.addChild(this.cmdBlessedWarReport);
        this.addChild(this.cmdBlessedWarStart);
        this.addChild(this.cmdBlessedWarConfig);
        this.addChild(this.cmdBlessedWarVersion);

        this.addRequirements(RequirementHasPerm.get(Perm.BASECOMMAND));
    }


    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //
    @Override
    public List<String> getAliases() { return MConf.get().aliasesBlessedWar; }
}
