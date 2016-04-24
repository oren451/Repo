#pragma once

using namespace std;

public MilkyCheeseProduct: public MilkProduct
{
   private:
	char* name;
	int amountOfAdditions;

   public:  
	virtual int calculatePrice();
	virtual void print();
}
