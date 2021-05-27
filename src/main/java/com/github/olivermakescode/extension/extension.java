package com.github.olivermakescode.extension;

import net.fabricmc.api.ModInitializer;

public class extension implements ModInitializer {
	public static BoolRuleHelper villagerTradeLock;
	public static BoolRuleHelper disableMutual;
	public static BoolRuleHelper itemCooldown;
	public static BoolRuleHelper ePearlEnable;
	public static BoolRuleHelper chorusEnable;
	public static BoolRuleHelper shieldEnable;
	public static BoolRuleHelper attackCool;
	public static BoolRuleHelper fallingBlocks;
	@Override
	public void onInitialize() {
		GameruleHelper.start();

		villagerTradeLock = (BoolRuleHelper) GameruleHelper.register("villagerTradeLock",true);
		disableMutual = (BoolRuleHelper) GameruleHelper.register("disableMutualExclusiveEnchantments",false);
		itemCooldown = (BoolRuleHelper) GameruleHelper.register("itemCooldown",true);
		ePearlEnable = (BoolRuleHelper) GameruleHelper.register("enderPearlCooldown",true);
		chorusEnable = (BoolRuleHelper) GameruleHelper.register("chorusFruitCooldown", true);
		shieldEnable = (BoolRuleHelper) GameruleHelper.register("shieldCooldown", true);
		attackCool = (BoolRuleHelper) GameruleHelper.register("attackCooldown", true);
		fallingBlocks = (BoolRuleHelper) GameruleHelper.register("fallingBlocks", true);

		swapRow.register();
		nicknames.registerCommand();
		locateEntityCommand.register();
		musicCommand.register();
	}
}
