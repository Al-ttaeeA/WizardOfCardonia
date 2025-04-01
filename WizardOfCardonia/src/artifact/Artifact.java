/***********************************************************************
 * Wizard of Cardonia
 * Copyright (C) 2025 A2Z Studios
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 ***********************************************************************/

package artifact;

public abstract class Artifact {
	protected final String name;
	protected final ArtifactType type;
	
	public Artifact(String name, ArtifactType type) {
		this.name = name;
		this.type = type;
	}
	
	public Artifact() {
		this.name = "Empty Artifact";
		this.type = ArtifactType.COMMON;
	}
	
	public abstract Artifact copy();
	
	public abstract void use();
	
	public abstract String toString();
	
	public String getName() {
		return name;
	}
	
	public ArtifactType getType() {
		return type;
	}
	
	public int getPrice() {
		if(type == ArtifactType.COMMON) {
			return 100;
		}
		else {
			return 150;
		}
	}
}
