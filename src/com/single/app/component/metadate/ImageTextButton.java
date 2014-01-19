package com.single.app.component.metadate;

public class ImageTextButton extends ButtonModule{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imageSrc;
	private String disableImageSrc;
	private String group;
	
	public ImageTextButton(String groupName, String name) {
		super(groupName, name);
	}

	
	
	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getDisableImageSrc() {
		return disableImageSrc;
	}

	public void setDisableImageSrc(String disableImageSrc) {
		this.disableImageSrc = disableImageSrc;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	@Override
	protected ImageTextButton clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (ImageTextButton) super.clone();
	}
}
