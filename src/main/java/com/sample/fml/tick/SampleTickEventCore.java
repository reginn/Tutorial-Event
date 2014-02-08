package com.sample.fml.tick;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

@Mod(modid = SampleTickEventCore.MODID, version = SampleTickEventCore.VERSION)
public class SampleTickEventCore
{
	public static final String MODID = "TickEvent";
	public static final String VERSION = "0.0.0";

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		/*
		 * Eventの登録
		 */
		FMLCommonHandler.instance().bus().register(this);
	}

	/*
	 * 各種Ticking処理を書く
	 * サンプルとしていい例が思い浮かばなかったのでとりあえず使い方だけ.
	 */
	@SubscribeEvent
	public void onServerTick(TickEvent.ServerTickEvent event)
	{
		/*
		 * ここにServerに関するtick処理を書く.
		 */
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event)
	{
		/*
		 * ここにClientに関するtick処理を書く.
		 */
	}

	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event)
	{
		/*
		 * ここにWorldに関するtick処理を書く.
		 * event.worldでWorldのインスタンスを取得できる.
		 */
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		/*
		 * ここにPlayerに関するtick処理を書く.
		 * event.playerでEntityPlayerのインスタンスを取得できる.
		 */
	}

	@SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event)
	{
		/*
		 * ここにRenderに関するtick処理を書く.
		 * event.renderTickTimeでtick timeを取得できる.
		 */
	}
}
