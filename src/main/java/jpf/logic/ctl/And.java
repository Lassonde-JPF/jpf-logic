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

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a CTL formula that is a conjunction (and) of two formulas.
 * 
 * @author Neena Govindhan
 * @author Jonas Laya
 * @author Jessie Leung
 * @author Paul Sison
 * @author Franck van Breugel
 * @author Anto Nanah Ji
 */
public class And extends CTLFormula {
	private CTLFormula left;
	private CTLFormula right;

	/**
	 * Initializes this CTL formula as the conjunction (and) of the given {@code left} and {@code right} subformulas.
	 * 
	 * @param left the left subformula of this and formula
	 * @param right the right subformula of this and formula
	 */
	public And(CTLFormula left, CTLFormula right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hashCode = 1;
		hashCode = prime * hashCode + this.left.hashCode();
		hashCode = prime * hashCode + this.right.hashCode();
		return hashCode;
	}

	@Override
	public boolean equals(Object object) {
		if (object != null && this.getClass() == object.getClass()) {
			And other = (And) object;
			return this.left.equals(other.left) && this.right.equals(other.right);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "(" + this.left + " && " + this.right + ")";
	}

	/**
	 * Returns the left subformula of this formula.
	 * 
	 * @return the left subformula of this formula
	 */
	public CTLFormula getLeft() {
		return this.left;
	}

	/**
	 * Returns the right subformula of this formula.
	 * 
	 * @return the right subformula of this formula
	 */
	public CTLFormula getRight() {
		return this.right;
	}  

	@Override
	public Set<String> getAliases() {
		Set<String> set = new HashSet<String>();
		set.addAll(this.left.getAliases());
		set.addAll(this.right.getAliases());
		return set;
	}

	@Override
	public CTLFormula simplify() {
		CTLFormula left = this.left.simplify();
		CTLFormula right = this.right.simplify();
		if (left instanceof False || right instanceof False) {
			return new False();
		} else if (left instanceof True) {
			return right;
		} else if (right instanceof True) {
			return left;
		} else {
			return new And(left, right);
		}
	}
}

