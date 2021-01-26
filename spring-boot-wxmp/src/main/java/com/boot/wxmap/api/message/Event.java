package com.boot.wxmap.api.message;

/**
 * 事件枚举
 */
public enum Event {


	/**
	 * 订阅，以及扫码订阅
	 */
	subscribe("subscribe"),
	/**
	 * 取消订阅
	 */
	unsubscribe("unsubscribe"),
	/**
	 * 扫码（已关注）
	 */
	SCAN("SCAN"),
	/**
	 * 上报地理位置
	 */
	LOCATION("LOCATION"),
	/**
	 * 菜单事件，点击菜单拉取消息时的事件推送
	 */
	CLICK("CLICK"),
	/**
	 * 菜单事件，点击菜单跳转链接时的事件推送
	 */
	VIEW("VIEW"),
	
	/**
	 * 菜单事件，scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框的事件推送
	 */
	scancode_waitmsg("scancode_waitmsg"),
	
	/**
	 * 菜单事件，scancode_push：扫码推事件的事件推送
	 */
	scancode_push("scancode_push"),
	
	/**
	 * pic_sysphoto：弹出系统拍照发图的事件推送
	 */
	pic_sysphoto("pic_sysphoto"),
	
	/**
	 * pic_photo_or_album：弹出拍照或者相册发图的事件推送
	 */
	pic_photo_or_album("pic_photo_or_album"),
	
	/**
	 * pic_weixin：弹出微信相册发图器的事件推送
	 */
	pic_weixin("pic_weixin"),
	
	/**
	 * location_select：弹出地理位置选择器的事件推送
	 */
	location_select("location_select"),
	
	/**
	 * 点击菜单跳转小程序的事件推送
	 */
	view_miniprogram("view_miniprogram"),
	
	/**
	 * 微信认证事件推送：资质认证成功（此时立即获得接口权限）
	 */
	qualification_verify_success("qualification_verify_success"),
	
	/**
	 * 微信认证事件推送：资质认证失败
	 */
	qualification_verify_fail("qualification_verify_fail"),
	
	/**
	 * 微信认证事件推送：名称认证成功（即命名成功）
	 */
	naming_verify_success("naming_verify_success"),
	
	/**
	 * 微信认证事件推送：名称认证失败（这时虽然客户端不打勾，但仍有接口权限）
	 */
	naming_verify_fail("naming_verify_fail"),
	
	/**
	 * 微信认证事件推送：年审通知
	 */
	annual_renew("annual_renew"),
	
	/**
	 * 微信认证事件推送：认证过期失效通知审通知
	 */
	verify_expired("verify_expired"),
	
	/**
	 * 消息管理--群发接口--事件推送群发结果
	 */
	MASSSENDJOBFINISH("MASSSENDJOBFINISH"),
	
	/**
	 * 消息管理--模板消息接口--事件推送
	 */
	TEMPLATESENDJOBFINISH("TEMPLATESENDJOBFINISH"),
	
	/**
	 * 空
	 */
	none(null)
	;
	
	private String name;
	
	private Event(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
