package com.tiaa.assignment.model;

public class Input {

	public int machine;
	public int bolt;
	public int assemble;

	public Input(int machine, int bolt, int assemble) {
		this.machine = machine;
		this.bolt = bolt;
		this.assemble = assemble;
	}

	@Override
	public String toString() {
		return "Input [machine=" + machine + ", bolt=" + bolt + ", assemble=" + assemble + "]";
	}

}
