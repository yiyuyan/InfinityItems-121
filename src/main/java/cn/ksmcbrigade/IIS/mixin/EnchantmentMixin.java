package cn.ksmcbrigade.IIS.mixin;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow @Final private Component description;

    @Inject(method = "canEnchant",at = @At("RETURN"),cancellable = true)
    public void can(ItemStack p_44689_, CallbackInfoReturnable<Boolean> cir){
        if(this.description.getString().equalsIgnoreCase("enchantment.minecraft.infinity")) cir.setReturnValue(true);
    }
}