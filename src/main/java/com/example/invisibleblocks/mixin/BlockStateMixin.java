package com.example.invisibleblocks.mixin;

import com.example.invisibleblocks.InvisibleBlocksMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/** Hides every block model that has no culled face too: flowers, grass, ferns, torches, etc. */
@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class BlockStateMixin {
    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void invisibleblocks$getRenderType(CallbackInfoReturnable<BlockRenderType> cir) {
        if (!InvisibleBlocksMod.enabled) return;
        if (!InvisibleBlocksMod.isVisible((BlockState) (Object) this)) {
            cir.setReturnValue(BlockRenderType.INVISIBLE);
        }
    }
}
