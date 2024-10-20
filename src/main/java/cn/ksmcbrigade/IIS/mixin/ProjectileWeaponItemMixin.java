package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import cn.ksmcbrigade.IIS.utils.ArrowAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {
    @Inject(method = "createProjectile",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ProjectileWeaponItem;customArrow(Lnet/minecraft/world/entity/projectile/AbstractArrow;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/projectile/AbstractArrow;",shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILSOFT,cancellable = true)
    public void arrow(Level p_331008_, LivingEntity p_330781_, ItemStack p_330846_, ItemStack p_331497_, boolean p_331305_, CallbackInfoReturnable<Projectile> cir, ArrowItem arrowitem, AbstractArrow abstractarrow, Item var8){
        if(InfinityItems.HAS(p_331497_,p_331008_)){
            ((ArrowAccessor)abstractarrow).set(true);
        }
    }
}
