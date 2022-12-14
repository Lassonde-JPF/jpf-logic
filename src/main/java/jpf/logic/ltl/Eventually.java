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

package jpf.logic.ltl;

import java.util.Set;

/**
 * This class represents an LTL formula that is the eventually (diamond) of a formula.
 * 
 * @author Neena Govindhan
 * @author Jonas Laya
 * @author Jessie Leung
 * @author Paul Sison
 * @author Franck van Breugel
 * @author Anto Nanah Ji
 */
public class Eventually extends Formula {
	private Formula formula;

	/**
	 * Initializes this LTL formula as the eventually of the given formula.
	 * 
	 * @param formula the subformula of this eventually formula
	 */
	public Eventually(Formula formula) {
		this.formula = formula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.formula.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (object != null && this.getClass() == object.getClass()) {
			Eventually other = (Eventually) object;
			return this.formula.equals(other.formula);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "F " + this.formula;
	}

	/**
	 * Returns the subformula of this formula.
	 * 
	 * @return the subformula of this formula
	 */
	public Formula getFormula() {
		return this.formula;
	}
	
	@Override
	public Set<String> getAliases() {
		return this.formula.getAliases();
	}
	
	@Override
	public Formula simplify() {
		Formula formula = this.formula.simplify();
		if (formula instanceof True) {
			return new True();
		} else if (formula instanceof False) {
			return new False();
		} else {
			return new Eventually(formula);
		}
	}
}