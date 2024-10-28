package cn.ksmcbrigade.epw.mixin;

import cn.ksmcbrigade.epw.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockStateBaseMixin {

    @Inject(method = "entityCanStandOn",at = @At("RETURN"),cancellable = true)
    public void noStand(BlockGetter p_60635_, BlockPos p_60636_, Entity entity, CallbackInfoReturnable<Boolean> cir){
        if((entity instanceof Player player) && player.isFallFlying() && Config.ENABLED.get()){
            cir.setReturnValue(false);
        }
    }

    @Inject(method = {"isAir"},at = @At("RETURN"),cancellable = true)
    public void noFull(CallbackInfoReturnable<Boolean> cir){
        Minecraft MC = Minecraft.getInstance();
        if(MC==null) return;
        Player player = MC.player;
        if(player!=null && player.isFallFlying() && Config.ENABLED.get()){
            cir.setReturnValue(true);
        }
    }
}
