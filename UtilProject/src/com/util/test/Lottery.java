
package com.util.test;


/**
 * The Enum Lottery.
 */
public enum Lottery {
	
	/** The tycp. */
	TYCP("体育彩票"),
/** The flcp. */
FLCP("福利彩票");
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new lottery.
	 *
	 * @param name the name
	 */
	Lottery(String name){
		this.name=name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	};
	
}
