package de.kuscheltiermafia.kingdoms.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Items {

    //Debug Items
    public static ItemStack spacer;
    public static ItemStack page_up;
    public static ItemStack page_down;
    public static ItemStack no_page_up;
    public static ItemStack no_page_down;
    public static ItemStack placeholder;

    //Misc
    public static ItemStack focken;
    public static ItemStack toe;
    public static ItemStack ascend_item;
    public static ItemStack descend_item;

    //Weapons
    public static ItemStack iron_sword;

    //Armor
    public static ItemStack funky_sneaker;

    //Blueprints
    public static ItemStack blueprint_arch_tower_1;
    public static ItemStack blueprint_bakery_1;
    public static ItemStack blueprint_camp_1;
    public static ItemStack blueprint_farm_1;
    public static ItemStack blueprint_lumberjack_1;
    public static ItemStack blueprint_mine_1;
    public static ItemStack blueprint_quarry_1;

    public static void initializeItems() {

        //Misc Items
        focken = new ItemBuilder().setMaterial(Material.DARK_OAK_BUTTON).setID("focken").setCustomName("§5Focken")
                .addLoreLine("§7- §dFantastisches orientiertes cooles kurioses entanguliertes Neutron §7 -")
                .addLoreLine("§7oder kurz, FOCKEN!")
                .addLoreLine("§7Keine Ahnung was das ist...").setMaxStackSize(1).show().build();
        toe = new ItemBuilder().setMaterial(Material.BOOK).setID("tome_of_eras").setCustomName("§5Tome of Eras").addGlint().setMaxStackSize(1).show().build();
        ascend_item = new ItemBuilder().setMaterial(Material.ENDER_EYE).setID("ascend_item").setCustomName("§bAscend").setMaxStackSize(1).show().build();
        descend_item = new ItemBuilder().setMaterial(Material.ENDER_PEARL).setID("descend_item").setCustomName("§bDescend").setMaxStackSize(1).show().build();

        //Weapons
        iron_sword = new ItemBuilder().setMaterial(Material.IRON_SWORD).setID("iron_sword").setCustomName("§fIron Sword")
                .setStrength(5)
                .setCritChance(2)
                .setCritDamage(5)
                .setMaxStackSize(1).show().build();

        //Armor
        funky_sneaker = new ItemBuilder().setMaterial(Material.GOLDEN_BOOTS).setID("funky_sneaker").setCustomName("§6Funky Sneaker")
                .setHealth(20)
                .setDefense(10)
                .setSpeed(5)
                .setOverheal(2)
                .setMaxStackSize(1).show().build();

        //Debug Items
        spacer = new ItemBuilder().setMaterial(Material.GRAY_STAINED_GLASS_PANE).setID("spacer").setCustomName("Spacer").hideTooltip().setMaxStackSize(1).build();
        page_up = new ItemBuilder().setMaterial(Material.ARROW).setID("page_up_indicator").setCustomName("§4Next Page").setMaxStackSize(1).build();
        page_down = new ItemBuilder().setMaterial(Material.ARROW).setID("page_down_indicator").setCustomName("§4Previous Page").setMaxStackSize(1).build();
        no_page_up = new ItemBuilder().setMaterial(Material.BARRIER).setID("spacer").setCustomName("§4No next Page available").setMaxStackSize(1).build();
        no_page_down = new ItemBuilder().setMaterial(Material.BARRIER).setID("spacer").setCustomName("§4No previous Page available").setMaxStackSize(1).build();
        placeholder = new ItemBuilder().setMaterial(Material.STRUCTURE_VOID).setID("placeholder").setCustomName("§5§l§kA§r§7 PLACEHOLDER §r§5§l§kA").setMaxStackSize(1).build();
    }

}
