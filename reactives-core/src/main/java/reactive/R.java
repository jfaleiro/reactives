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

/**
 * A reactive proxy that allow for active, local modification of the type
 * delegate.
 * 
 * @author jfaleiro
 *
 * @param <T>
 *            type of the delegate
 */
public interface R<T> extends RF<T> {

	/**
	 * Sets a new state. Changing the state forces a new reaction.
	 * 
	 * @param value
	 *            new value
	 */
	void set(T value);

}
