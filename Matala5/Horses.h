/*
 * Horses.h
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Mammals.h"
#include <vector>

class Horses: public Mammals
{
private:
	const std::string mRaceType;

public:
	Horses() : Mammals(), mRaceType("Default") {};
	Horses(const Horses& horses);
	Horses(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime,
			const int timemonthpregnancy, const int milkamountperday,
			const std::string& racetype);
	void print();
};


