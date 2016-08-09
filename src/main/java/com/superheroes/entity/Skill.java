package com.superheroes.entity;

public class Skill {

    private final String name;
    private final double destructionPower;

    public Skill(String name, double destructionPower) {
        this.name = name;
        this.destructionPower = destructionPower;
    }

    public String getName() {
        return name;
    }

    public double getDestructionPower() {
        return destructionPower;
    }
}
