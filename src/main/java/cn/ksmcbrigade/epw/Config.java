package cn.ksmcbrigade.epw;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLED = BUILDER
            .comment("the enable of the EPW module.")
            .define("enabled", false);

    public static final ModConfigSpec CONFIG = BUILDER.build();
}
