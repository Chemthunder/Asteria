package silly.chemthunder.asteria.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import silly.chemthunder.asteria.api.ColorableItem;

public class ClockOfVexingItem extends Item implements ColorableItem {
    public ClockOfVexingItem(Settings settings) {
        super(settings);
    }

    @Override
    public int startColor(ItemStack itemStack) {
        return 0xFF62ffae;
    }

    @Override
    public int endColor(ItemStack itemStack) {
        return 0xFF008741;
    }

    @Override
    public int backgroundColor(ItemStack itemStack) {
        return 0xF00f2e1e;
    }
}
