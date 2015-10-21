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

import java.util.UUID;
import java.util.function.Supplier;

import reactive.core.RDmap;
import reactive.core.RFLambda;
import tapestry.DMap;
import tapestry.local.DMapLocal;

/**
 * Distributed map reactive utility methods. By default uses a DMapLocal
 * instance.
 * 
 * @author jfaleiro
 *
 */
public class Reactives {

	private static DMap<String, ?> DMAP = new DMapLocal<>();

	/**
	 * Sets a new DMap implementation.
	 * 
	 * @param dmap
	 *            new DMap
	 */
	public static void setDmap(DMap<String, ?> dmap) {
		DMAP = dmap;
	}

	/**
	 * Resets to default.
	 */
	public static void reset() {
		DMAP = new DMapLocal<>();
	}

	public static final Reactives instance() {
		return new Reactives(DMAP);
	}

	private final DMap<String, ?> dmap;

	public Reactives(DMap<String, ?> dmap) {
		super();
		this.dmap = dmap;
	}

	/**
	 * Creates new un-labeled reactive proxy.
	 * 
	 * @return new un-labeled reactive proxy.
	 */
	public <V> R<V> reactive() {
		return reactive(UUID.randomUUID().toString());
	}

	/**
	 * Creates new labeled reactive proxy.
	 * 
	 * @return new labeled reactive proxy.
	 */
	@SuppressWarnings("unchecked")
	public <V> R<V> reactive(String label) {
		return new RDmap<V>((DMap<String, V>) dmap, label);
	}

	/**
	 * Creates new un-labeled reactive proxy with an initial value.
	 * 
	 * @return new un-labeled reactive proxy.
	 */
	public <V> R<V> reactive(V initial) {
		return reactive(UUID.randomUUID().toString(), initial);
	}

	/**
	 * Creates new labeled reactive proxy with an initial value.
	 * 
	 * @return new labeled reactive proxy.
	 */
	@SuppressWarnings("unchecked")
	public <V> R<V> reactive(String label, V initial) {
		return new RDmap<V>((DMap<String, V>) dmap, label, initial);
	}

	/**
	 * Creates new un-labeled lambda reactive proxy.
	 * 
	 * @return new un-labeled lambda reactive proxy.
	 */
	public <V> RF<V> reactive(Supplier<V> s, RF<?>... sources) {
		return reactive(UUID.randomUUID().toString(), s, sources);
	}

	/**
	 * Creates new labeled lambda reactive proxy.
	 * 
	 * @return new labeled lambda reactive proxy.
	 */
	@SuppressWarnings("unchecked")
	public <V> RF<V> reactive(String label, Supplier<V> s, RF<?>... sources) {
		return new RFLambda<V>((DMap<String, V>) dmap, label, s, sources);
	}
}
