package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import net.minecraft.advancements.critereon.UsedTotemTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cn.ksmcbrigade.IIS.InfinityItems.HAS;

@Mixin(UsedTotemTrigger.class)
public abstract class TotemMixin {

    @Inject(method = "trigger",at = @At("TAIL"))
    public void SetAir(ServerPlayer p_74432_, ItemStack p_74433_, CallbackInfo ci){
        if(HAS(p_74433_,p_74432_.serverLevel())){
            ItemStack itemStack = Items.TOTEM_OF_UNDYING.getDefaultInstance();
            itemStack.enchant(InfinityItems.getINFINITY(p_74432_.serverLevel()),1);
            if(!p_74432_.getMainHandItem().isEmpty()){
                p_74432_.setItemInHand(InteractionHand.OFF_HAND,itemStack);
            }
            else{
                p_74432_.setItemInHand(InteractionHand.MAIN_HAND,itemStack);
            }
        }
    }
}
