/*
 * Fish.h
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Animal.h"

class Fish: public virtual Animal
{
private:
	const int mDandruffAmount;
	const int mGillsAmount;

public:
	Fish(): Animal(), mDandruffAmount(0), mGillsAmount(0) {};
	Fish(const Fish& fish);
	Fish(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime, const int dandruff, const int gills);
	void print();
};

