package re.domi.fastchest.mixin;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import re.domi.fastchest.config.Config;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    @Inject(method = "toUpdatePacket", at = @At("HEAD"), cancellable = true)
    private void fastchest$cancelUpdatePacket(CallbackInfoReturnable<? extends Packet<?>> cir) {
        if (Config.simplifiedChest) {
            cir.setReturnValue(null);
        }
    }

    @Inject(method = "toInitialChunkDataNbt", at = @At("HEAD"), cancellable = true)
    private void fastchest$cancelInitialChunkData(CallbackInfoReturnable<NbtCompound> cir) {
        if (Config.simplifiedChest) {
            cir.setReturnValue(new NbtCompound());
        }
    }
}
