#pragma once

class YogurtProduct: public MilkProduct
{
public:
	YogurtProduct();
	YogurtProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int fat, MilkType milktype, int colorcount);
	virtual ~YogurtProduct();
	virtual int calculatePrice();
	virtual void print();
};

