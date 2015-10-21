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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;

import org.jgroups.JChannel;
import org.junit.Test;

import tapestry.jgroups.DMapJgroups;

public class ReactivesTest {

	private static void print(String s) {
		System.out.println(s);
	}

	@Test
	public void test() {
		Reactives.reset();
		final Reactives r = Reactives.instance();
		R<Integer> a = r.reactive("a");
		R<Integer> b = r.reactive("b", 3);

		RF<Integer> c = r.reactive("c", () -> a.eval() + b.eval(), a, b);
		assertFalse(c.isValid());
		assertTrue(b.isValid());
		assertFalse(a.isValid());
		
		a.onReaction((i) -> print("a = " + i));
		b.onReaction((i) -> print("b = " + i));
		c.onReaction((i) -> print("c = " + i));
		
		a.set(3);
		b.set(1);
		a.set(7);
	}

	@Test
	public void testReplica() {
		Reactives.reset();
		final Reactives r = Reactives.instance();
		R<Integer> a = r.reactive("a");
		R<Integer> b = r.reactive("b", 3);

		RF<Integer> c = r.reactive("c", () -> a.eval() + b.eval(), a, b);
		RF<Integer> c2 = r.reactive("c");

		a.onReaction((i) -> print("a = " + i));
		b.onReaction((i) -> print("b = " + i));
		c.onReaction((i) -> print("c = " + i));
		c2.onReaction((i) -> print("c' = " + i));
		
		a.set(3);
		b.set(1);
		a.set(7);
	}
	
//	@Test
	public void testReplicaNonLocal() throws Exception {
		Reactives.setDmap(new DMapJgroups<String, Serializable>(new JChannel(), "test-replica-non-local"));
		final Reactives r = Reactives.instance();
		R<Integer> a = r.reactive("a");
		R<Integer> b = r.reactive("b", 3);

		RF<Integer> c = r.reactive("c", () -> a.eval() + b.eval(), a, b);
		RF<Integer> c2 = r.reactive("c");

		a.onReaction((i) -> print("a = " + i));
		b.onReaction((i) -> print("b = " + i));
		c.onReaction((i) -> print("c = " + i));
		c2.onReaction((i) -> print("c' = " + i));
		
		a.set(3);
		b.set(1);
		a.set(7);
	}

}
