/**
 *
 *   Reactives - a framework for distributed, on-demand, real-time computations
 *
 *   Copyright (C) 2006 Jorge M. Faleiro Jr.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Affero General Public License as published
 *   by the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Affero General Public License for more details.
 *
 *   You should have received a copy of the GNU Affero General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package reactive;

import java.util.function.Consumer;

/**
 * A reactive proxy, does not allow local modifications.
 * 
 * @author jfaleiro
 *
 * @param <T>
 *            delegate type
 */
public interface RF<T> {

	/**
	 * validity indicator.
	 * 
	 * @return <code>true</code> if valid
	 */
	default boolean isValid() {
		return eval() != null;
	}

	/**
	 * Bind this reative to a source reactive.
	 * 
	 * @param source
	 *            the source reactive to bind to.
	 */
	void bind(RF<?> source);

	/**
	 * Getter for the label of this reactive.
	 * 
	 * @return the label of this reactive
	 */
	String getLabel();

	/**
	 * The current value of the evaluation of this reactive.
	 * 
	 * @return
	 */
	T eval();

	/**
	 * Register a lambda function (or a consumer function) for execution on
	 * reaction of this instance.
	 * 
	 * @param consumer
	 *            the consumer function.
	 */
	void onReaction(Consumer<T> consumer);

}
