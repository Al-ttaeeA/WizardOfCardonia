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
	
	protected final int id;
	
	public Artifact(int id, String name, ArtifactType type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public Artifact() {
		this.id = 0;
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
	
	public int getId() {
    	return id;
    }
	
	public int getPrice() {
		if(type == ArtifactType.COMMON) {
			return 100;
		}
		else {
			return 150;
		}
	}
	
	public abstract String save();
	
	public static Artifact loadArtifact(String line) {
		String[] tokens = line.split(",");
		
		String name = tokens[1];
		ArtifactType type = ArtifactType.valueOf(tokens[2]);
		
		switch(Integer.parseInt(tokens[0])) {
		case 1:{
			double mult = Double.parseDouble(tokens[3]);
			return new AllArtifact(name, type, mult);
		}
		case 2:{
			double mult = Double.parseDouble(tokens[3]);
			return new CorArtifact(name, type, mult);
		}
		case 3:{
			int cards = Integer.parseInt(tokens[3]);
			return new HandArtifact(name, type, cards);
		}
		case 4:{
			double mult = Double.parseDouble(tokens[3]);
			return new IntArtifact(name, type, mult);
		}
		case 5:{
			int mana = Integer.parseInt(tokens[3]);
			return new ManaArtifact(name, type, mana);
		}
		case 6:{
			double mult = Double.parseDouble(tokens[3]);
			return new SaleArtifact(name, type, mult);
		}
		case 7:{
			double mult = Double.parseDouble(tokens[3]);
			return new StrArtifact(name, type, mult);
		}
		}
		
		return new AllArtifact();
	}
}
