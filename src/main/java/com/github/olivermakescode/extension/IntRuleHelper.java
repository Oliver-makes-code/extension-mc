package com.github.olivermakescode.extension;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class IntRuleHelper implements GameRuleInterface {
    private int value;
    private GameRules.Key<GameRules.IntRule> rule;

    public IntRuleHelper(String name, int defaultValue) {
        this.value = defaultValue;
        this.rule = GameRuleRegistry.register(name, GameRules.Category.MISC, GameRuleFactory.createIntRule(defaultValue));
    }

    @Override
    public void updateValue() {
        this.value = GameruleHelper.server.getGameRules().getInt(rule);
    }

    public int getValue() {
        this.updateValue();
        return this.value;
    }
}
