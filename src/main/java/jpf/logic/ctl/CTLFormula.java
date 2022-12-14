/*
 * Copyright (C)  2021
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package jpf.logic.ctl;

import java.util.Random;

import jpf.logic.DefaultFormula;

/**
 * A CTL formula.
 * 
 * @author Franck van Breugel
 */
public class CTLFormula extends DefaultFormula {
	
	/**
	 * Randomness.
	 */
	private static final Random RANDOM = new Random();

	/**
	 * Returns a random formula of at most the given depth.
	 * 
	 * @param the maximum depth of the formula
	 * @return a random formula of at most the given depth
	 */
	public static CTLFormula random(int depth) {
		final int BASE_CASES = 3;
		final int INDUCTIVE_CASES = 13;
		final int MAX_INDEX = 4;

		if (depth == 0) {
			switch (RANDOM.nextInt(BASE_CASES)) {
			case 0:
				return new True();
			case 1:
				return new False();
			case 2:
				String alias = "a" + RANDOM.nextInt(MAX_INDEX + 1);
				return new Alias(alias);
			default:
				throw new IllegalArgumentException("Illegal argument for switch in base case");
			}
		} else {
			switch (RANDOM.nextInt(BASE_CASES + INDUCTIVE_CASES)) {
			case 0:
				return new True();
			case 1:
				return new False();
			case 2:
				String alias = "b" + RANDOM.nextInt(MAX_INDEX + 1);
				return new Alias(alias);
			case 3:
				return new Not(CTLFormula.random(depth - 1));
			case 4:
				return new And(CTLFormula.random(depth - 1), CTLFormula.random(depth - 1));
			case 5:
				return new Or(CTLFormula.random(depth - 1), CTLFormula.random(depth - 1));
			case 6:
				return new Implies(CTLFormula.random(depth - 1), CTLFormula.random(depth - 1));
			case 7:
				return new Iff(CTLFormula.random(depth - 1), CTLFormula.random(depth - 1));
			case 8:
				return new ExistsAlways(CTLFormula.random(depth - 1));
			case 9:
				return new ForAllAlways(CTLFormula.random(depth - 1));
			case 10:
				return new ExistsEventually(CTLFormula.random(depth - 1));
			case 11:
				return new ForAllEventually(CTLFormula.random(depth - 1));
			case 12:
				return new ExistsNext(CTLFormula.random(depth - 1));
			case 13:
				return new ForAllNext(CTLFormula.random(depth - 1));
			case 14:
				return new ExistsUntil(CTLFormula.random(depth - 1), CTLFormula.random(depth - 1));
			case 15:
				return new ForAllUntil(CTLFormula.random(depth - 1), CTLFormula.random(depth - 1));
			default:
				throw new IllegalArgumentException("Illegal argument for switch in inductive case");
			}
		}
	}

	/**
	 * Default depth of a formula.
	 */
	private static final int DEFAULT_DEPTH = 5;

	/**
	 * Returns a random formula.
	 * 
	 * @return a random formula
	 */
	public static CTLFormula random() {
		return CTLFormula.random(CTLFormula.DEFAULT_DEPTH);
	}
	
	@Override
	public CTLFormula simplify() {
		return this;
	}
}
