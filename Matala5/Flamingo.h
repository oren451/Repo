/*
 * Flamingo.h
 *
 *  Created on: May 19, 2016
 *      Author: orenk
 */
#include "Birds.h"
#include <vector>

class Flamingo : public Birds {

private:
	const int mAverageHeight;

public:
	Flamingo() : Birds(), mAverageHeight(0) {};
	Flamingo(const Flamingo& flamingo);
	Flamingo(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime,
			const int incubationtime, const int averageheight);
	void print();
};



