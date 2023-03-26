package com.kevin.tutorialmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

// Click the scroll wheel to view class definition
// Renaming in Intellij - Shift + F6
public class EightBallItem extends Item {

    public EightBallItem(Properties properties){
        super(properties);
    }

    // What happens when we right click with this item in our hand
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        // Check if we are using it with our right hand
        // Use level.isClientSide() to check if we are on the client or the server
        if(level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            outputRandomNumber(player);
            // Will have a cooldown of 1 second
            player.getCooldowns().addCooldown(this, 20);
        }
        return super.use(level, player, hand);
    }

    private void outputRandomNumber(Player player){
        String message = String.format("Your random number is %d", getRandomNumber());
        player.sendSystemMessage(Component.literal(message));
    }

    private int getRandomNumber(){
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }
}
