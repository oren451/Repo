/*
 * Birds.h
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Animal.h"

class Birds: public Animal {
private:
	const int mIncubatioTime;
public:
	Birds(): Animal(), mIncubatioTime(0) {};
	Birds(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime, const int incubationtime);
	Birds(const Birds& birds);
	virtual ~Birds();
	void print();
};


