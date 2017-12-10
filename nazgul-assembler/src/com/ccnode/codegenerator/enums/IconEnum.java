package com.ccnode.codegenerator.enums;

public enum IconEnum
{
	DEFAULT("default", "icon/mybatis_new_6.png", "icon/mybatis_new_5.png"),  LIANGGONG("lianggong", "icon/lianggong_2.png", "icon/lianggong_3.png"),  POAIXOA("paoxiao", "icon/paoxiao_1.png", "icon/paoxiao_2.png");

	private String iconName;
	private String mapperIconPlace;
	private String mapperXmlIconPlace;

	private IconEnum(String iconName, String mapperIconPlace, String mapperXmlIconPlace)
	{
		this.iconName = iconName;
		this.mapperIconPlace = mapperIconPlace;
		this.mapperXmlIconPlace = mapperXmlIconPlace;
	}

	public String getIconName()
	{
		return this.iconName;
	}

	public void setIconName(String iconName)
	{
		this.iconName = iconName;
	}

	public String getMapperIconPlace()
	{
		return this.mapperIconPlace;
	}

	public void setMapperIconPlace(String mapperIconPlace)
	{
		this.mapperIconPlace = mapperIconPlace;
	}

	public String getMapperXmlIconPlace()
	{
		return this.mapperXmlIconPlace;
	}

	public void setMapperXmlIconPlace(String mapperXmlIconPlace)
	{
		this.mapperXmlIconPlace = mapperXmlIconPlace;
	}

	public static IconEnum getIconEnum(String name)
	{
		IconEnum[] values = values();
		for (IconEnum value : values) {
			if (name.equals(value.getIconName())) {
				return value;
			}
		}
		return null;
	}
}
