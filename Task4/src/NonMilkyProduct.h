/*
 * NonMilkyProduct.h
 *
 *  Created on: Apr 21, 2016
 *      Author: orenk
 */

#pragma once

class NonMilkyProduct: public MilkProduct {

private:
	int mAmountOfNonMilkAdditions;
	char* mNonMilkNames[];

public:
	NonMilkyProduct();
	NonMilkyProduct(char* name, int id, ShelfRow place, int weight,
			ProductType type, ExposureValue exposure,int fat, MilkType milktype,
			int colorcount, int amountOfNonMilkAdditions, char* nonMilkNames[]);
	virtual ~NonMilkyProduct();
	virtual int calculatePrice();
	virtual void print();
};
