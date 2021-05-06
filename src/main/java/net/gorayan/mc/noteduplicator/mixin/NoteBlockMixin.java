package net.gorayan.mc.noteduplicator.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoteBlock.class)
public class NoteBlockMixin {

    @Inject(method = "getPlacementState", at = @At("RETURN"), cancellable = true)
    private void inject(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {

        CompoundTag compoundTag = ctx.getStack().getTag();
        if (compoundTag == null) {
            return;
        }
        int i = compoundTag.getInt("note");

        BlockState blockState = cir.getReturnValue();
        cir.setReturnValue(blockState.with(NoteBlock.NOTE, MathHelper.clamp(i, 0, 24)));

    }

}
