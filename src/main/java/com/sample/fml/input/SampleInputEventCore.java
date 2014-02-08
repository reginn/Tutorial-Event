package com.sample.fml.input;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ChatComponentTranslation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

@Mod(modid = SampleInputEventCore.MODID, version = SampleInputEventCore.VERSION)
public class SampleInputEventCore
{
	public static final String MODID = "FMLInputEvent";
	public static final String VERSION = "0.0.0";

	/*
	 * KeyBindingのインスタンスを作成
	 * クライアントのみなので, SideOnlyアノテーションを付与するか, ClientProxyなどで分離すること.
	 * KeyBindingの引数は(Keyの内部名, 指定キー, オプション画面でのキーバインドのカテゴリ).
	 * "Key.InputKey"や"Sample:Key.InputKey"はunlocalized nameであり,
	 * 他の場合と同様にen_US.langなどでローカライズできる.
	 * 詳細はassets/input/lang./en_US.langを参照
	 */
	@SideOnly(Side.CLIENT)
	public static final KeyBinding inputKey = new KeyBinding("key.inputKey.name", Keyboard.KEY_I, "sample.inputEvent.name");

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		/*
		 * KeyBindingを登録する.
		 * クライアントのみなので以下のようにするかClientProxyで分離する.
		 */
		if (event.getSide() == Side.CLIENT)
		{
			ClientRegistry.registerKeyBinding(inputKey);
		}

		/*
		 * Eventの登録.
		 * 今回はこのクラスにEventメソッドを書いているのでthisでよい.
		 * 別クラスに書いた場合はregister(new HogeKeyInputEvent())のようにする.
		 */
		FMLCommonHandler.instance().bus().register(this);
	}

	/*
	 * KeyInputEventの実装
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void inputKey(InputEvent.KeyInputEvent event)
	{
		/*
		 * inputKeyが押されるとisPressed()がtrueになる.
		 */
		if (inputKey.isPressed())
		{
			FMLClientHandler
					.instance()
					.getClient()
					.thePlayer
					.addChatMessage(new ChatComponentTranslation("KEY_I is Pressed"));
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void inputMouse(InputEvent.MouseInputEvent event)
	{
		/*
		 * lwjglのMouseを直接利用する.
		 * KeyBindingに登録することもできるが, 基本的にマウスのボタンは3つともバニラで使用されているので推奨されない.
		 * 0 : 左クリック
		 * 1 : 右クリック
		 * 2 : ホイール(センター)クリック
		 */
		if (Mouse.isButtonDown(0))
		{
			FMLClientHandler
					.instance()
					.getClient()
					.thePlayer
					.addChatMessage(new ChatComponentTranslation("Mouse Left Button is Pressed"));
		}
	}

}
