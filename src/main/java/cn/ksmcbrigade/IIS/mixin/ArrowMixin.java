package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.utils.ArrowAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Arrow.class)
public abstract class ArrowMixin extends AbstractArrow {

    protected ArrowMixin(EntityType<? extends AbstractArrow> p_331098_, Level p_331626_) {
        super(p_331098_, p_331626_);
    }

    @ModifyArg(method = "doPostHurtEffects",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;<init>(Lnet/minecraft/core/Holder;IIZZ)V"),index = 1)
    public int dur(int p_19516_){
        if(((ArrowAccessor)this).get()) return Integer.MAX_VALUE;
        return p_19516_;
    }
}
