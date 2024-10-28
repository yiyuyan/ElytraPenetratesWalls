package cn.ksmcbrigade.epw;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(ElytraPenetratesWalls.MODID)
public class ElytraPenetratesWalls
{
    public static final String MODID = "epw";

    public ElytraPenetratesWalls(IEventBus modEventBus, ModContainer modContainer)
    {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.CONFIG);
    }
}
