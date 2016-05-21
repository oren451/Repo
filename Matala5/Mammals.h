/*
 * Mammals.h
 *
 *  Created on: May 16, 2016
 *      Author: orenk
 */
#include "Animal.h"

class Mammals: public virtual Animal {

private:
	const int mTimeMonthPregnancy;
	const int mMilkAmountPerDay;

public:
	Mammals():Animal()
				, mTimeMonthPregnancy(0)
				, mMilkAmountPerDay(0) {};
	Mammals(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime,
			const int timemonthpregnancy, const int milkamountperday);
	Mammals(const Mammals& mammals);
	void print();
};


