package org.notionsmp.fastchestrevived.mixin;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.notionsmp.fastchestrevived.config.Config;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void fastchest$simplifyRenderType(BlockState state, CallbackInfoReturnable<BlockRenderType> cir) {
        // Handle both ChestBlock and EnderChestBlock
        if (Config.simplifiedChest) {
            Block block = state.getBlock();
            if (block instanceof ChestBlock || block instanceof EnderChestBlock) {
                cir.setReturnValue(BlockRenderType.MODEL);
            }
        }
    }
}