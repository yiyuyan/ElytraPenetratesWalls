package cn.ksmcbrigade.epw.mixin;

import cn.ksmcbrigade.epw.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public class BlockShapeMixin {
    @Inject(method = "getShape",at = @At("RETURN"),cancellable = true)
    public void shape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_, CallbackInfoReturnable<VoxelShape> cir){
        Minecraft MC = Minecraft.getInstance();
        if(MC==null) return;
        Player player = MC.player;
        if(player!=null && player.isFallFlying() && Config.ENABLED.get()){
            cir.setReturnValue(Shapes.empty());
        }
    }

    @Inject(method = {"isCollisionShapeFullBlock*"},at = @At("RETURN"),cancellable = true)
    public void noFull(BlockState p_181242_, BlockGetter p_181243_, BlockPos p_181244_, CallbackInfoReturnable<Boolean> cir){
        Minecraft MC = Minecraft.getInstance();
        if(MC==null) return;
        Player player = MC.player;
        if(player!=null && player.isFallFlying() && Config.ENABLED.get()){
            cir.setReturnValue(false);
        }
    }

    @Inject(method = {"isAir"},at = @At("RETURN"),cancellable = true)
    public void noFull(BlockState state, CallbackInfoReturnable<Boolean> cir){
        Minecraft MC = Minecraft.getInstance();
        if(MC==null) return;
        Player player = MC.player;
        if(player!=null && player.isFallFlying() && Config.ENABLED.get()){
            cir.setReturnValue(true);
        }
    }
}
