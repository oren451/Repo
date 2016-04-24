#pragma once

using namespace std;

public FruitProduct: public FarmProduct
{
   private:
	int sugarAmount;
	
   public:  
	virtual int calculatePrice();
	virtual void print();
}
