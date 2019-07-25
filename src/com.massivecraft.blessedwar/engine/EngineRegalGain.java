package com.massivecraft.blessedwar.engine;

import com.massivecraft.massivecore.Engine;
import com.massivecraft.massivecore.money.Money;
import com.massivecraft.massivecore.money.MoneyMixinVault;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class EngineRegalGain extends Engine
{
    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    private static EngineRegalGain i = new EngineRegalGain();
    public static EngineRegalGain get() { return i; }

    // -------------------------------------------- //
    // FACTION JOINED
    // -------------------------------------------- //

    //TODO:
//    @EventHandler(priority = EventPriority.HIGH)
//    public void onRegalGain(final )
//    {
//      if(Money.enabled())
//      {
//
//      }
//    }
}
