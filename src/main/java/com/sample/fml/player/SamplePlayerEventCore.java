package com.sample.fml.player;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.util.ChatComponentText;

@Mod(modid = SamplePlayerEventCore.MODID, version = SamplePlayerEventCore.VERSION)
public class SamplePlayerEventCore
{
	public static final String MODID = "FMLPlayerEvent";
	public static final String VERSION = "0.0.0";

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		/*
		 * Eventの登録.
		 * 今回はこのクラスにEventメソッドを書いているのでthisでよい.
		 * 別クラスに書いた場合はregister(new HogeEventClass())のようにする.
		 */
		FMLCommonHandler.instance().bus().register(this);
	}

	/*
	 * 作業台またはプレイヤーインベントリで作成されたアイテムを取得したときに呼ばれる.
	 * (作業台またはプレイヤーインベントリの右側のスロットにあるアイテムを左クリックしたとき)
	 * なおサーバーとクライアントで2回呼ばれる.
	 */
	@SubscribeEvent
	public void onCraftedHook(PlayerEvent.ItemCraftedEvent event)
	{
		String craftedItemName = event.crafting.getDisplayName();
		event.player.addChatComponentMessage(new ChatComponentText(craftedItemName + " has Crafted"));
	}

	/*
	 * かまどで精錬されたアイテムを取得したときに呼ばれる
	 * なおサーバーとクライアントで2回呼ばれる.
	 */
	@SubscribeEvent
	public void onSmeltedHook(PlayerEvent.ItemSmeltedEvent event)
	{
		String smeltedItemName = event.smelting.getDisplayName();
		event.player.addChatComponentMessage(new ChatComponentText(smeltedItemName + " has smelted"));
	}

	/*
	 * 落ちているアイテムを拾ったときに呼ばれる.
	 * サーバー側のみなので1回だけ呼ばれる.
	 */
	@SubscribeEvent
	public void onPickedUpHook(PlayerEvent.ItemPickupEvent event)
	{
		String pickedUpItemName = event.pickedUp.getEntityItem().getDisplayName();
		event.player.addChatComponentMessage(new ChatComponentText(pickedUpItemName + " has picked up"));
	}
}
