package net.gorayan.mc.noteduplicator.mixin;

import javafx.scene.control.TextFormatter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TextColor;
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
        itemStack.setCustomName(new LiteralText("\u00A7" + "f" + getNoteName(i) + " " + i));

    }

    private String getNoteName(int i) {
        int mod = i % 12;
        switch (mod) {
            case 0:
                return "F#";
            case 1:
                return "G";
            case 2:
                return "G#";
            case 3:
                return "A";
            case 4:
                return "A#";
            case 5:
                return "B";
            case 6:
                return "C";
            case 7:
                return "C#";
            case 8:
                return "D";
            case 9:
                return "D#";
            case 10:
                return "E";
            case 11:
                return "F";
            default:
                return null;
        }
    }

}
