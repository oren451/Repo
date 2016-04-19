#include "Product.h"

using namespace std;

public NonMilkyProduct: public MilkProduct
{
   private:
	char* name;
	int amountOfNonMilkAdditions;
	char* nonMilkNames[];

   public:  
	virtual int calculatePrice();
	virtual void print();
}
