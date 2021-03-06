package com.company.selenium.api;

/**
 * Models a SELECT tag, providing helper methods to select and deselect options.
 */

public enum DropDownSelectorOptions {
	/**
	 * Select all options that display text matching the argument. That is, when
	 * given "Bar" this would select an option like:
	 *
	 * <option value="foo" option>;
	 *
	 * @param text The visible text to match against
	 * @throws NoSuchElementException If no matching option elements are found
	 */
	VISIBLETEXT,
	/**
	 * Select the option at the given index. This is done by examining the "index"
	 * attribute of an element, and not merely by counting.
	 *
	 * @param index The option at this index will be selected
	 * @throws NoSuchElementException If no matching option elements are found
	 */
	INDEX,
	/**
	 * Select all options that have a value matching the argument. That is, when
	 * given "foo" this would select an option like:
	 *
	 * <option value="foo" option>;
	 *
	 * @param value The value to match against
	 * @throws NoSuchElementException If no matching option elements are found
	 */
	VALUE

}
