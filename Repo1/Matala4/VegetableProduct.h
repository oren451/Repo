#pragma once

using namespace std;

public VegetableProduct: public FarmProduct
{
   private:
	int VitaminAmount;
	
   public:  
	virtual int calculatePrice();
	virtual void print();
}
