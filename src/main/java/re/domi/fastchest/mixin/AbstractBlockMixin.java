package re.domi.fastchest.mixin;

import net.minecraft.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import re.domi.fastchest.FastChestTags;
import re.domi.fastchest.config.Config;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void fastchest$simplifyRenderType(BlockState state, CallbackInfoReturnable<BlockRenderType> cir) {
        // Handle both ChestBlock and EnderChestBlock
        if (Config.simplifiedChest) {
            if ((state.getBlock() instanceof ChestBlock && state.isIn(FastChestTags.COMPATIBLE_CHEST_BLOCKS)) ||
                    (state.getBlock() instanceof EnderChestBlock)) {
                cir.setReturnValue(BlockRenderType.MODEL);
            }
        }
    }
}