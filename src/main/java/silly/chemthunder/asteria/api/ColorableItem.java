package silly.chemthunder.asteria.api;

import net.minecraft.item.ItemStack;

/*
Ported from Nitrogne
 */
public interface ColorableItem {
    int startColor(ItemStack stack);
    int endColor(ItemStack stack);
    int backgroundColor(ItemStack stack);
}
