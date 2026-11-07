package com.example.invisibleblocks.mixin;

import com.example.invisibleblocks.InvisibleBlocksMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "shouldDrawSide", at = @At("HEAD"), cancellable = true)
    private static void invisibleblocks$shouldDrawSide(BlockState state, BlockState otherState,
                                                       Direction side, CallbackInfoReturnable<Boolean> cir) {
        if (!InvisibleBlocksMod.enabled) return;
        // Whitelisted blocks always draw (even when buried); everything else never draws.
        cir.setReturnValue(InvisibleBlocksMod.isVisible(state));
    }
}
