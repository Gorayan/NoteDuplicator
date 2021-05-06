package net.gorayan.mc.noteduplicator.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "getPickStack", at = @At("RETURN"))
    private void inject(BlockView world, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cir) {

        if (!((Object) this instanceof NoteBlock)) {
            return;
        }
        int i = state.get(NoteBlock.NOTE);
        ItemStack itemStack = cir.getReturnValue();
        itemStack.setTag(new CompoundTag());
        itemStack.getTag().putInt("note", i);

    }

}
