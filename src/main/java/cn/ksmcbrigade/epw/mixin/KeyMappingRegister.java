package cn.ksmcbrigade.epw.mixin;

import cn.ksmcbrigade.epw.client.EPWClient;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class KeyMappingRegister {

    @Unique
    private static boolean elytraPenetratesWalls$init = false;

    @Inject(method = "setShaderGlintAlpha(D)V",at = @At("TAIL"))
    private static void setInit(double p_268332_, CallbackInfo ci){
        if(!elytraPenetratesWalls$init){
            Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, EPWClient.EPW);
            Minecraft.getInstance().options.keyMappings = ArrayUtils.add(Minecraft.getInstance().options.keyMappings, EPWClient.STOP);
            elytraPenetratesWalls$init = true;
        }
    }
}
