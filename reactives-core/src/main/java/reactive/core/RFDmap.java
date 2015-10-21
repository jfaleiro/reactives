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

import java.util.Objects;
import java.util.function.Consumer;

import reactive.RF;
import tapestry.BoundListener;
import tapestry.DMap;
import tapestry.EntryEvent;

/**
 * RMap-backed reactive.
 * 
 * @author jfaleiro
 *
 * @param <T>
 *            type delegate
 */
public class RFDmap<T> implements RF<T> {

	private final String label;

	private final DMap<String, T> dmap;

	public RFDmap(DMap<String, T> dmap, String label) {
		super();
		this.label = label;
		this.dmap = dmap;
	}

	@Override
	public String getLabel() {
		return label;
	}

	public DMap<String, T> getDmap() {
		return dmap;
	}

	@Override
	public T eval() {
		return dmap.get(label);
	}

	@Override
	public void onReaction(Consumer<T> consumer) {
		dmap.addBoundListener(new BoundListener<EntryEvent<String, T>>() {
			@Override
			public void onBoundEvent(EntryEvent<String, T> entry) {
				if (Objects.equals(label, entry.getKey())) {
					consumer.accept(entry.getValue());
				}
			}
		});
	}

	@Override
	public void bind(RF<?> source) {
		dmap.addBoundListener(new BoundListener<EntryEvent<String, T>>() {
			@Override
			public void onBoundEvent(EntryEvent<String, T> entry) {
				if (Objects.equals(source.getLabel(), entry.getKey())) {
					react();
				}
			}
		});
	}

	protected void react() {
		dmap.put(label, eval());
	}

}
