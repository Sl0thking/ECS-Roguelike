package de.sloth.entity;

import java.util.LinkedList;
import java.util.List;

import de.sloth.component.Component;

public class Entity {
	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + ", components=" + components + "]";
	}

	private int id;
	private String name;
	private List<Component> components;
	
	public Entity(int id, String name, List<Component> components) {
		super();
		this.id = id;
		this.name = name;
		this.components = components;
	}
	
	public Entity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.components = new LinkedList<Component>();
	}	
	
	public Entity() {
		super();
		this.id = 0;
		this.name = "Unknown Entity";
		this.components = new LinkedList<Component>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public void addComponent(Component comp) {
		this.components.add(comp);
	}
	
	public List<Component> getComponents() {
		return this.components;
	}
	
	public List<Class<?>> getComponentClasses() {
		List<Class<?>> compClasses = new LinkedList<Class<?>>();
		for(Component comp : this.components) {
			compClasses.add(comp.getClass());
		}
		return compClasses;
	}
	
	public Component getComponent(Class<?> compClass) {
		for(Component comp : components) {
			if(comp.getClass().equals(compClass)) {
				return comp;
			}
		}
		return null;
	}
}