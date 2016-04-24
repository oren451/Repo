
#pragma once

using namespace std;

public MilkProduct: public Product
{
   private:
	char* name;
	int fat;
	
   public:  
	virtual int calculatePrice();
	virtual void print();
}
