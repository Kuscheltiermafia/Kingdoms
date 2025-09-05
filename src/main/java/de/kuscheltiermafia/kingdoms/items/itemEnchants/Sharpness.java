package de.kuscheltiermafia.kingdoms.items.itemEnchants;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.items.ItemType;
import de.kuscheltiermafia.kingdoms.stats.ItemStatCalculator;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import org.bukkit.inventory.ItemStack;

public class Sharpness extends ItemEnchant {
    public Sharpness() {
        super("Sharpness", "sharpness", EnchantType.NORMAL, new String[]{"Increases your damage by 15% per Level"}, 7, new ItemType[]{ItemType.SWORD, ItemType.AXE, ItemType.DAGGER});
    }

    @Override
    public void onApply(ItemStack item, int level) {
        ItemStatCalculator.addMultiplicativeModifier(item, Stat.DAMAGE, this.enchantmentID, 1 + (0.15 * level));
        ItemHandler.updateItem(item);
    }

    @Override
    public void onRemove(ItemStack item) {
        ItemStatCalculator.removeMultiplicativeModifier(item, Stat.DAMAGE, this.enchantmentID);
        ItemHandler.updateItem(item);
    }
}
