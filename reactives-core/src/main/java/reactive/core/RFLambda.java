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
package reactive.core;

import java.util.function.Supplier;

import reactive.RF;
import tapestry.DMap;

/**
 * Lambda RMap-backed reactive.
 * 
 * @author jfaleiro
 *
 * @param <T>
 *            delegate type
 */
public class RFLambda<T> extends RFDmap<T> implements RF<T> {

	private final Supplier<T> supplier;

	public RFLambda(DMap<String, T> dmap, String label, Supplier<T> supplier,
			RF<?>... sources) {
		super(dmap, label);
		this.supplier = supplier;
		for (final RF<?> s : sources) {
			this.bind(s);
		}
	}

	@Override
	public T eval() {
		try {
			return supplier.get();
		} catch (RuntimeException e) {
			return null;
		}
	}

}
