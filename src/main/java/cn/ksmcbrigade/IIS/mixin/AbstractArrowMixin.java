package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.utils.ArrowAccessor;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AbstractArrow.class)
public class AbstractArrowMixin implements ArrowAccessor {
    @Unique
    private boolean infinityItems_121$has = false;

    @Override
    public void set(boolean has) {
        this.infinityItems_121$has = has;
    }

    @Override
    public boolean get() {
        return this.infinityItems_121$has;
    }
}
