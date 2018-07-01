package com.tiaa.assignment.model;

import com.tiaa.assignment.constants.Equipments;

public class Default implements Equipment {

	@Override
	public Equipments getEquipments() {
		return Equipments.Default;
	}
}
