/*
 * Animal.h
 *
 *  Created on: May 16, 2016
 *      Author: orenk
 */
#pragma once
#include <string>

class Animal {

private:
	const std::string mColor;
	Animal** mOffspringList;
	const int mOffspringAmount;
	const int mAverageLifetime;
public:
	Animal();
	Animal(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime);
	Animal(const Animal& animal);
	virtual ~Animal();
	void print();
};


