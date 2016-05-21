/*
 * GoldFish.h
 *
 *  Created on: May 20, 2016
 *      Author: orenk
 */
#include "Fish.h"
#include "Mammals.h"

class GoldFish: public Mammals, public Fish
{
private:
	const int mAverageWeight;
	const int mAverageLength;
public:
	GoldFish(): Mammals(), Fish(), mAverageWeight(0), mAverageLength(0) {};
	GoldFish(const GoldFish& goldfish);
	GoldFish(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime,
			const int timemonthpregnancy,
			const int milkamountperday, const int dandruff, const int gills,
			const int averageweight, const int averagelength);
	void print();
};

