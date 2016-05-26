//
// Created by orenk on 5/25/16.
//

#include "CElement.h"

CElement::CElement(){}
CElement::CElement(int num):m_num(num){}
CElement::CElement(const CElement& other):m_num(other.getNum()){}
CElement::~CElement(){}

int CElement::getNum() const {return m_num;}
void CElement::setNum(int num){m_num=num;}