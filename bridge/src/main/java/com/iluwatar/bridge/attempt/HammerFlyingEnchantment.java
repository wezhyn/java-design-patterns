package com.iluwatar.bridge.attempt;

import com.iluwatar.bridge.Enchantment;
import com.iluwatar.bridge.Hammer;
import com.iluwatar.bridge.Weapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HammerFlyingEnchantment implements Weapon, Enchantment {
    private static final Logger LOGGER=LoggerFactory.getLogger(Hammer.class);

    @Override
    public void wield() {
        LOGGER.info("The hammer is wielded.");
        onActivate();
    }

    @Override
    public void swing() {
        LOGGER.info("The hammer is swinged.");
        apply();
    }

    @Override
    public void unwield() {
        LOGGER.info("The hammer is unwielded.");
        onDeactivate();
    }


    @Override
    public void onActivate() {
        LOGGER.info("The item begins to glow faintly.");
    }

    @Override
    public void apply() {
        LOGGER.info("The item flies and strikes the enemies finally returning to owner's hand.");
    }

    @Override
    public void onDeactivate() {
        LOGGER.info("The item's glow fades.");
    }

    @Override
    public Enchantment getEnchantment() {
        return null;
    }
}
