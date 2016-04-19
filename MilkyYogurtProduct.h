#include "Product.h"

using namespace std;

public MilkyYogurtProduct: public MilkProduct
{
   public:  
	virtual int calculatePrice();
	virtual void print();
}
