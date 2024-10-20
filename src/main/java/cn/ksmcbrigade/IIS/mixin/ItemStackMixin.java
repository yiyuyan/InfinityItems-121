package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract ItemStack copy();

    @Inject(method = "setCount",at = @At("HEAD"),cancellable = true)
    public void set(int p_41765_, CallbackInfo ci){
        if(!InfinityItems.init) return;
        if(p_41765_<=1 && EnchantmentHelper.getTagEnchantmentLevel(InfinityItems.INFINITY, this.copy()) !=0){
            ci.cancel();
        }
    }
}
