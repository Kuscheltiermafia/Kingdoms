package de.kuscheltiermafia.kingdoms.items.itemEnchants;

import de.kuscheltiermafia.kingdoms.items.ItemHandler;
import de.kuscheltiermafia.kingdoms.items.ItemType;
import de.kuscheltiermafia.kingdoms.stats.ItemStatCalculator;
import de.kuscheltiermafia.kingdoms.stats.Stat;
import org.bukkit.inventory.ItemStack;

public class Torment extends ItemEnchant{

    public Torment() {
        super("Torment", "torment", EnchantType.SPECIAL, new String[]{"Increases Damage by +300%"}, 1, new ItemType[]{ItemType.AXE});
    }

    @Override
    public void onApply(ItemStack item, int level) {
        ItemStatCalculator.addMultiplicativeModifier(item, Stat.DAMAGE, this.enchantmentID, 4.0);
        ItemHandler.updateItem(item);
    }

    @Override
    public void onRemove(ItemStack item) {
        ItemStatCalculator.removeMultiplicativeModifier(item, Stat.DAMAGE, this.enchantmentID);
        ItemHandler.updateItem(item);
    }
}
