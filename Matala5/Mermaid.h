/*
 * Mermaid.h
 *
 *  Created on: May 20, 2016
 *      Author: orenk
 */
#include "Fish.h"
#include "Mammals.h"

class Mermaid: public Mammals, public Fish {
private:
	const std::string mFirstName;
	const std::string mLastName;
public:
	Mermaid(): Mammals(), Fish(), mFirstName("Firstname"), mLastName("Lastname") {};
	Mermaid(const Mermaid& mermaid);
	Mermaid(const std::string& color, Animal**& offspringlist,
			const int offspringamount, const int averagelifetime,
			const int timemonthpregnancy,
			const int milkamountperday, const int dandruff, const int gills,
			const std::string& firstname, const std::string& lastname);
	void print();
};


