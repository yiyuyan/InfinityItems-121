package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockItem.class)
public abstract class BlockItemInfinityMixin extends Item {
    public BlockItemInfinityMixin(Properties p_41383_) {
        super(p_41383_);
    }

    @Redirect(method = "place",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;consume(ILnet/minecraft/world/entity/LivingEntity;)V"))
    public void useOn(ItemStack ITEM, int p_326311_, LivingEntity p_326200_) {
        if(!InfinityItems.HAS(ITEM,p_326200_.level()) || ITEM.getItem().equals(Items.POWDER_SNOW_BUCKET) || ITEM.getCount()>1){
            ITEM.consume(p_326311_,p_326200_);
        }
    }
}
