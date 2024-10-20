package cn.ksmcbrigade.IIS;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.Commands;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mod(InfinityItems.MODID)
@EventBusSubscriber(modid = InfinityItems.MODID)
public class InfinityItems
{
    public static final String MODID = "iis";
    public static int exp = 7;

    public static boolean init = false;
    public static Holder<Enchantment> INFINITY;

    public InfinityItems(IEventBus modEventBus, ModContainer modContainer) throws IOException {

        File file = new File("config/iis-config.json");
        if(!file.exists()){
            JsonObject json = new JsonObject();
            json.addProperty("exp",exp);
            Files.write(file.toPath(),json.toString().getBytes());
        }
        exp = JsonParser.parseString(Files.readString(file.toPath())).getAsJsonObject().get("exp").getAsInt();
    }

    @SubscribeEvent
    public static void RegisterCommand(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal("inject").executes(context -> {
            Player entity = (Player)context.getSource().getEntity();
            if (entity != null) {
                if(!entity.getMainHandItem().isEmpty()){
                    if(entity.experienceLevel>=exp){

                        entity.getMainHandItem().enchant(getINFINITY(context.getSource().getLevel()),1);
                        entity.giveExperienceLevels(-exp);
                    }
                    else{
                        entity.sendSystemMessage(Component.nullToEmpty(I18n.get("commands.iis.cannot_xp").replace("{x}",String.valueOf(exp))));
                    }
                }
                else if(!entity.getOffhandItem().isEmpty()){
                    if(entity.experienceLevel>=exp){
                        entity.getOffhandItem().enchant(getINFINITY(context.getSource().getLevel()),1);
                        entity.giveExperienceLevels(-exp);
                    }
                    else{
                        entity.sendSystemMessage(Component.nullToEmpty(I18n.get("commands.iis.cannot_xp").replace("{x}",String.valueOf(exp))));
                    }
                }
                else{
                    entity.sendSystemMessage(Component.translatable("commands.iis.empty"));
                }
            }
            return 0;
        }));
    }

    public static boolean HAS(ItemStack itemStack,Level level){
        return EnchantmentHelper.getTagEnchantmentLevel(getINFINITY(level), itemStack) !=0;
    }

    public static Holder<Enchantment> getINFINITY(Level level){
        if(!init) init(level);
        return INFINITY;
    }

    private static void init(Level level){
        INFINITY = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolder(Enchantments.INFINITY).get();
        init = true;
    }
}
