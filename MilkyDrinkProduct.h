#include "Product.h"

using namespace std;

public MilkyDrinkProduct: public MilkProduct
{
   public:  
	virtual int calculatePrice();
	virtual void print();
}
