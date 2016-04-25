/*
 * MilkyCheeseProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */
#pragma once

class MilkyCheeseProduct: public MilkProduct {

private:
	int mAddition;
public:

	MilkyCheeseProduct();
	MilkyCheeseProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int fat, MilkType milktype,
			int colorcount, int addition);
	virtual ~MilkyCheeseProduct();
	virtual int calculatePrice();
	virtual void print();
};

