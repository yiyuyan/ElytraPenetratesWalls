package cn.ksmcbrigade.epw.client;

import cn.ksmcbrigade.epw.Config;
import cn.ksmcbrigade.epw.ElytraPenetratesWalls;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = ElytraPenetratesWalls.MODID,value = Dist.CLIENT)
public class EPWClient {
    public static final KeyMapping EPW = new KeyMapping("key.epw.epw", InputConstants.KEY_F8,KeyMapping.CATEGORY_GAMEPLAY);
    public static final KeyMapping STOP = new KeyMapping("key.epw.stop", InputConstants.KEY_F9,KeyMapping.CATEGORY_GAMEPLAY);

    @SubscribeEvent
    public static void input(InputEvent.Key event){
        Minecraft MC = Minecraft.getInstance();
        if(EPW.isDown()){
            Config.ENABLED.set(!Config.ENABLED.get());
            if(MC.player!=null){
                MC.player.displayClientMessage(Component.literal("ElytraPenetratesWalls").append(CommonComponents.SPACE).append(":").append(CommonComponents.SPACE).append(String.valueOf(Config.ENABLED.get())),true);
            }
        }
        if(STOP.isDown() && MC.player!=null && MC.player.isFallFlying() && MC.getConnection()!=null){
            MC.getConnection().getConnection().send(new ServerboundPlayerCommandPacket(MC.player, ServerboundPlayerCommandPacket.Action.START_FALL_FLYING));
        }

    }
}
